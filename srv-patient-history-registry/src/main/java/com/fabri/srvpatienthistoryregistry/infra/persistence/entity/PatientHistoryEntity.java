package com.fabri.srvpatienthistoryregistry.infra.persistence.entity;

import com.fabri.srvpatienthistoryregistry.domain.PatientHistory;
import com.fabri.srvpatienthistoryregistry.domain.vo.AppointmentStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@NoArgsConstructor
@Document("tb_patient_history")
public class PatientHistoryEntity {

    @Id
    private String id;
    @CreatedDate
    private Instant historyCreatedAt;
    private String appointmentId;
    private AppointmentStatus status;
    private Instant finishedAt;
    private DoctorPrescriptionEntity doctorPrescription;
    private TriageEntity triage;

    @Version
    private Long version;

    public PatientHistoryEntity(PatientHistory patientHistory) {
        this.id = patientHistory.getId();

        var patientAppointment = patientHistory.getPatientAppointment();
        this.appointmentId = patientAppointment.getAppointmentId();
        this.status = patientAppointment.getStatus();
        this.finishedAt = patientAppointment.getAppointmentFinishedAt();
        this.doctorPrescription = new DoctorPrescriptionEntity(patientAppointment.getDoctorPrescription());
        this.triage = TriageEntity.from(patientAppointment.getPatientTriage());
    }
}
