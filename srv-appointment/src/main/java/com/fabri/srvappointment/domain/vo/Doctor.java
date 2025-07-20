package com.fabri.srvappointment.domain.vo;

import com.fabri.srvappointment.infra.exception.DomainException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class Doctor {

    private Long doctorId;
    private String doctorName;
    private String doctorCrm;
    private String doctorEmail;
    private List<AvailableDoctorAgenda> availableDoctorAgendaList = new ArrayList<>();

    public Doctor(Long doctorId, String doctorName, String doctorEmail) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.doctorEmail = doctorEmail;
    }

    public static Doctor from(DoctorOutput doctor) {
        Objects.requireNonNull(doctor, "Failed to map DoctorOutput is null");

        return new Doctor(
                doctor.getDoctorId(),
                doctor.getDoctorName(),
                doctor.getDoctorCrm(),
                doctor.getDoctorEmail(),
                doctor.getAvailableAppointments()
        );
    }

    public Instant validate(LocalDate localDate, LocalTime localTime) {
        Objects.requireNonNull(localDate, "Failed to validate doctor date");
        Objects.requireNonNull(localTime, "Failed to validate doctor time");

        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        var appointmentDate = Instant.from(localDate.atTime(localTime));

        if (doctorHasNotThisDateAvailableOnHisAgenda(formatter, appointmentDate)) {
            throw new DomainException("Date of appointment does not available for given doctor");
        }

        return appointmentDate;
    }

    private boolean doctorHasNotThisDateAvailableOnHisAgenda(DateTimeFormatter formatter, Instant dateToAppoint) {
        return getAvailableDoctorAgendaList().stream()
                .map(AvailableDoctorAgenda::getDate)
                .noneMatch(doctorTime ->
                        formatter.format(doctorTime).equalsIgnoreCase(formatter.format(dateToAppoint))
                );
    }
}
