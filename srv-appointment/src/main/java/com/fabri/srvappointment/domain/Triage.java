package com.fabri.srvappointment.domain;

import com.fabri.srvappointment.application.io.StartPatientTriageInput;
import com.fabri.srvappointment.domain.event.FinishedPatientTriageEvent;
import com.fabri.srvappointment.domain.event.RejectedTriageEvent;
import com.fabri.srvappointment.domain.vo.Doctor;
import com.fabri.srvappointment.domain.vo.Medication;
import com.fabri.srvappointment.domain.vo.Patient;
import com.fabri.srvappointment.domain.vo.TriageStatus;
import com.fabri.srvappointment.infra.exception.DomainException;
import com.fabri.srvappointment.infra.externals.persistence.entity.TriageEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Getter
@AllArgsConstructor
public class Triage {

    private Long id;
    private Instant triageDate;
    private Instant appointmentDate;
    private Patient patient;
    private Doctor doctor;

    private String patientComplaint;
    private String patientAllergies;
    private List<Medication> patientTakenMedications;

    private TriageStatus status;
    private Long statusApprovedByUserId;
    private Instant statusUpdatedAt;
    private String rejectionReason;

    public Triage(Long id, StartPatientTriageInput triageInput, Patient patient, Doctor doctor) {
        Objects.requireNonNull(triageInput, "Check-in input cannot be null.");
        Objects.requireNonNull(patient, "Patient cannot be null.");
        Objects.requireNonNull(doctor, "Doctor cannot be null.");

        if (Optional.ofNullable(triageInput.patientComplaints()).orElse("").isBlank()) {
            throw new DomainException("Patient complaints cannot be blank.");
        }

        this.id = id;
        this.triageDate = Instant.now();
        this.doctor = doctor;
        this.patient = patient;
        this.patient.setPatientAge(triageInput.patientAge());
        this.patientAllergies = triageInput.patientAllergies();
        this.patientComplaint = triageInput.patientComplaints();
        this.appointmentDate = this.doctor.validate(triageInput.dateToAppoint(), triageInput.timeToAppoint());
        this.status = TriageStatus.PENDING_DOCTOR_APPROVAL;

        setPatientTakenMedications(triageInput);
    }

    public static Triage from(TriageEntity triageEntity) {
        if (triageEntity == null) {
            throw new DomainException("Check-in entity cannot be null.");
        }

        return new Triage(
                triageEntity.getId(),
                triageEntity.getTriageDate(),
                triageEntity.getAppointmentSuposedDate(),
                new Patient(triageEntity.getPatientId(), triageEntity.getPatientName(),
                        triageEntity.getPatientEmail()),
                new Doctor(triageEntity.getDoctorId(), triageEntity.getDoctorName(), triageEntity.getDoctorEmail()),
                triageEntity.getPatientComplaints(),
                triageEntity.getPatientAllergies(),
                Arrays.stream(triageEntity.getPatientTakenMedications().split(","))
                        .map(Medication::fromTriage)
                        .toList(),
                triageEntity.getTriageStatus(),
                triageEntity.getApprovedBy(),
                triageEntity.getApprovedAt(),
                triageEntity.getRejectionReason()
        );
    }

    public static Triage from(TriageEntity triageEntity, Patient patient, Doctor doctor) {
        return new Triage(
                triageEntity.getId(),
                triageEntity.getTriageDate(),
                triageEntity.getAppointmentSuposedDate(),
                patient,
                doctor,
                triageEntity.getPatientComplaints(),
                triageEntity.getPatientAllergies(),
                Arrays.stream(triageEntity.getPatientTakenMedications().split(","))
                        .map(Medication::fromTriage)
                        .toList(),
                triageEntity.getTriageStatus(),
                triageEntity.getApprovedBy(),
                triageEntity.getApprovedAt(),
                triageEntity.getRejectionReason()
        );
    }

    private void setPatientTakenMedications(StartPatientTriageInput triageInput) {
        if (!Optional.ofNullable(triageInput.patientTaking()).orElse(List.of()).isEmpty()) {
            this.patientTakenMedications = triageInput.patientTaking().stream()
                    .map(Medication::fromTriage)
                    .toList();
        }
    }

    public void updateStatus(Long doctorId, TriageStatus triageStatus) {
        if (this.status != TriageStatus.PENDING_DOCTOR_APPROVAL) {
            throw new DomainException("Check-in is already approved or rejected.");
        }
        if (triageStatus.equals(TriageStatus.PENDING_DOCTOR_APPROVAL)) {
            throw new DomainException("Check-in status cannot be set to PENDING.");
        }
        if (!Objects.equals(this.doctor.getDoctorId(), doctorId)) {
            throw new DomainException("Doctor ID does not match the check-in's doctor.");
        }
        this.status = triageStatus;
        this.statusApprovedByUserId = doctorId;
        this.statusUpdatedAt = Instant.now();
    }

    public IDomainEvent getEvent() {
        if (this.status.isApproved()) {
            return new FinishedPatientTriageEvent(this);
        } else if (this.status.isCancelled() || this.status.isError()) {
            return new RejectedTriageEvent(this);
        }
        throw new DomainException("Cannot create event for check-in with status: " + this.status);
    }

}
