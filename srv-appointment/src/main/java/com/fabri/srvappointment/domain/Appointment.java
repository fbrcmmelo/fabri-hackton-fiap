package com.fabri.srvappointment.domain;

import com.fabri.srvappointment.domain.vo.AppointmentStatus;
import com.fabri.srvappointment.domain.vo.DoctorPrescription;
import com.fabri.srvappointment.domain.vo.Exam;
import com.fabri.srvappointment.domain.vo.Medication;
import com.fabri.srvappointment.infra.externals.persistence.entity.AppointmentEntity;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Getter
public class Appointment {

    private Long appointmentId;
    private Triage triage;
    private Instant createdAt;
    private Instant appointmentAt;
    private Instant finishedAt;
    private AppointmentStatus status;
    private DoctorPrescription doctorPrescription;

    public Appointment(Long appointmentId, Triage triage) {
        Objects.requireNonNull(triage, "Triage cannot be null to create an Appointment");

        this.appointmentId = appointmentId;
        this.triage = triage;
        this.createdAt = Instant.now();
        this.appointmentAt = triage.getAppointmentDate();
        this.status = AppointmentStatus.CONFIRMED;
    }

    public static Appointment from(AppointmentEntity entity) {
        if (entity == null) {
            return null;
        }

        Appointment appointment = new Appointment(entity.getId(), Triage.from(entity.getTriage()));
        appointment.status = entity.getStatus();
        appointment.createdAt = entity.getCreatedAt();
        appointment.finishedAt = entity.getFinishedAt();
        appointment.doctorPrescription = DoctorPrescription.from(entity.getDoctorPrescription());

        return appointment;
    }

    public void createDoctorPrescription(String notes, List<Medication> medications, List<Exam> exams) {
        this.doctorPrescription = new DoctorPrescription(
                null,
                this.triage.getId(),
                this.getTriage().getDoctor().getDoctorCrm(),
                medications,
                exams,
                notes
        );
    }

    public void finishAppointment() {
        this.status = AppointmentStatus.FINISHED;
        this.finishedAt = Instant.now();
    }

}
