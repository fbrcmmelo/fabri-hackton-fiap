package com.fabri.srvappointment.domain.vo;

import com.fabri.srvappointment.infra.client.user.UserOutput;
import com.fabri.srvappointment.infra.exception.DomainException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.*;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class Doctor {

    private Long doctorId;
    private String doctorName;
    private String doctorCrm;
    private String doctorEmail;
    private Instant lastDoctorAppointment;
    private Long appointmentDurationInMinutes = 30L;

    public Doctor(Long doctorId, String doctorName, String doctorEmail) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.doctorEmail = doctorEmail;
    }

    public static Doctor from(UserOutput doctor) {
        Objects.requireNonNull(doctor, "Failed to map DoctorOutput is null");

        return new Doctor(
                doctor.getId(),
                doctor.getName(),
                doctor.getCrm(),
                doctor.getEmail(),
                doctor.getNextAppointmentTime(),
                doctor.getAppointmentDurationInMinutes()
        );
    }

    public Instant validate(LocalDate localDate, LocalTime localTime) {
        if (patientHasntSendAndDateToAppointment(localDate, localTime)) {
            if (this.lastDoctorAppointment == null) {
                // Doctor with avaliable next time
                this.lastDoctorAppointment = Instant.now().plus(Duration.ofMinutes(10));
            } else {
                this.lastDoctorAppointment.plus(Duration.ofMinutes(appointmentDurationInMinutes));
            }
            return lastDoctorAppointment;
        }

        var appointmentDate = localDate.atTime(localTime).atZone(ZoneId.systemDefault()).toInstant();

        if (doctorHasNotThisDateAvailableOnHisAgenda(appointmentDate)) {
            throw new DomainException("Date of appointment does not available for given doctor");
        }

        return appointmentDate;
    }

    private static boolean patientHasntSendAndDateToAppointment(LocalDate localDate, LocalTime localTime) {
        return localDate == null || localTime == null;
    }

    private boolean doctorHasNotThisDateAvailableOnHisAgenda(Instant dateToAppoint) {
        if (this.lastDoctorAppointment == null) {
            return false;
        }

        Instant nextAvailableAppointment = this.lastDoctorAppointment.plus(Duration.ofMinutes(appointmentDurationInMinutes));
        return !dateToAppoint.isAfter(nextAvailableAppointment);
    }
}
