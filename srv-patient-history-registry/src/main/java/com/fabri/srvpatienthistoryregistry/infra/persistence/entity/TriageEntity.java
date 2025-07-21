package com.fabri.srvpatienthistoryregistry.infra.persistence.entity;

import com.fabri.srvpatienthistoryregistry.domain.vo.Medication;
import com.fabri.srvpatienthistoryregistry.domain.vo.PatientTriage;
import com.fabri.srvpatienthistoryregistry.domain.vo.TriageStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TriageEntity {

    private String id;
    private Instant triageDate;
    private Instant appointmentSuposedDate;
    private String approvedBy;
    private Instant approvedAt;
    private String rejectionReason;

    private String patientId;
    private Integer patientAge;
    private String patientName;
    private String patientEmail;

    private String doctorId;
    private String doctorName;
    private String doctorEmail;

    private String patientComplaints;
    private String patientAllergies;
    private String patientTakenMedications;

    private TriageStatus triageStatus;
    private Integer version;

    public static TriageEntity from(PatientTriage patientTriage) {
        TriageEntity triageEntity = new TriageEntity();
        triageEntity.setId(triageEntity.getId());
        triageEntity.setTriageDate(patientTriage.getTriageDate());
        triageEntity.setAppointmentSuposedDate(patientTriage.getAppointmentDate());
        triageEntity.setTriageStatus(patientTriage.getStatus());

        triageEntity.setApprovedBy(patientTriage.getStatusApprovedByUserId());
        triageEntity.setApprovedAt(patientTriage.getStatusUpdatedAt());
        triageEntity.setRejectionReason(patientTriage.getRejectionReason());

        triageEntity.setPatientId(patientTriage.getPatient().getPatientId());
        triageEntity.setPatientName(patientTriage.getPatient().getPatientName());
        triageEntity.setPatientEmail(patientTriage.getPatient().getPatientEmail());

        triageEntity.setDoctorId(patientTriage.getDoctor().getDoctorId());
        triageEntity.setDoctorName(patientTriage.getDoctor().getDoctorName());
        triageEntity.setDoctorEmail(patientTriage.getDoctor().getDoctorEmail());

        triageEntity.setPatientComplaints(patientTriage.getPatientComplaint());
        triageEntity.setPatientAge(patientTriage.getPatient().getPatientAge());
        triageEntity.setPatientAllergies(patientTriage.getPatientAllergies());
        triageEntity.setPatientTakenMedications(patientTriage.getPatientTakenMedications().stream().map(Medication::getName)
                .collect(Collectors.joining(",")));

        return triageEntity;
    }

}
