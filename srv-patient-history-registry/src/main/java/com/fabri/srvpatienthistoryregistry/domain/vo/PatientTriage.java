package com.fabri.srvpatienthistoryregistry.domain.vo;

import com.fabri.srvpatienthistoryregistry.domain.DomainException;
import com.fabri.srvpatienthistoryregistry.infra.persistence.entity.TriageEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public class PatientTriage {

    private String id;
    private Instant triageDate;
    private Instant appointmentDate;
    private Patient patient;
    private Doctor doctor;

    private String patientComplaint;
    private String patientAllergies;
    private List<Medication> patientTakenMedications;

    private TriageStatus status;
    private String statusApprovedByUserId;
    private Instant statusUpdatedAt;
    private String rejectionReason;

    public static PatientTriage from(TriageEntity triageEntity) {
        if (triageEntity == null) {
            throw new DomainException("Check-in entity cannot be null.");
        }

        return new PatientTriage(
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

}
