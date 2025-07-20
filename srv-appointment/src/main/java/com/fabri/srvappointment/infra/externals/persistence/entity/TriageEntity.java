package com.fabri.srvappointment.infra.externals.persistence.entity;

import com.fabri.srvappointment.domain.Triage;
import com.fabri.srvappointment.domain.vo.Medication;
import com.fabri.srvappointment.domain.vo.TriageStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_triage")
public class TriageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant triageDate;
    private Instant appointmentSuposedDate;
    private Long approvedBy;
    private Instant approvedAt;
    private String rejectionReason;

    private Long patientId;
    private Integer patientAge;
    private String patientName;
    private String patientEmail;

    private Long doctorId;
    private String doctorName;
    private String doctorEmail;

    private String patientComplaints;
    private String patientAllergies;
    private String patientTakenMedications;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TriageStatus triageStatus;

    @Version
    private Integer version;

    public static TriageEntity from(Triage triage) {
        TriageEntity triageEntity = new TriageEntity();
        triageEntity.setId(triage.getId());
        triageEntity.setTriageDate(triage.getTriageDate());
        triageEntity.setAppointmentSuposedDate(triage.getAppointmentDate());
        triageEntity.setTriageStatus(triage.getStatus());

        triageEntity.setApprovedBy(triage.getStatusApprovedByUserId());
        triageEntity.setApprovedAt(triage.getStatusUpdatedAt());
        triageEntity.setRejectionReason(triage.getRejectionReason());

        triageEntity.setPatientId(triage.getPatient().getPatientId());
        triageEntity.setPatientName(triage.getPatient().getPatientName());
        triageEntity.setPatientEmail(triage.getPatient().getPatientEmail());

        triageEntity.setDoctorId(triage.getDoctor().getDoctorId());
        triageEntity.setDoctorName(triage.getDoctor().getDoctorName());
        triageEntity.setDoctorEmail(triage.getDoctor().getDoctorEmail());

        triageEntity.setPatientComplaints(triage.getPatientComplaint());
        triageEntity.setPatientAge(triage.getPatient().getPatientAge());
        triageEntity.setPatientAllergies(triage.getPatientAllergies());
        triageEntity.setPatientTakenMedications(triage.getPatientTakenMedications().stream().map(Medication::getName)
                .collect(Collectors.joining(",")));

        return triageEntity;
    }

}
