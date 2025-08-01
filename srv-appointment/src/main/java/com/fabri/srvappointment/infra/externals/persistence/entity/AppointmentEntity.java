package com.fabri.srvappointment.infra.externals.persistence.entity;

import com.fabri.srvappointment.domain.Appointment;
import com.fabri.srvappointment.domain.vo.AppointmentStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_appointment")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @Column(nullable = false)
    private Instant createdAt;

    private Instant finishedAt;

    @JoinColumn(name = "doctor_prescription_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private DoctorPrescriptionEntity doctorPrescription;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "triage_id", nullable = false)
    private TriageEntity triage;

    @Version
    private Long version;

    public AppointmentEntity(Appointment appointment) {
        this.id = appointment.getAppointmentId();
        this.status = appointment.getStatus();
        this.createdAt = appointment.getCreatedAt();
        this.finishedAt = appointment.getFinishedAt();
        this.triage = TriageEntity.from(appointment.getTriage());
        this.version = appointment.getVersion();
    }
}
