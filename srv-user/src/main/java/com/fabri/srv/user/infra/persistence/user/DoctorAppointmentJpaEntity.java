package com.fabri.srv.user.infra.persistence.user;

import com.fabri.srv.user.domain.user.vo.DoctorAppointment;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_doctor_appointment")
public class DoctorAppointmentJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long triageId;

    @Column(nullable = false, unique = true)
    private Instant appointmentTime;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorJpaEntity doctor;

    public static DoctorAppointmentJpaEntity from(DoctorAppointment appointment) {
        DoctorAppointmentJpaEntity entity = new DoctorAppointmentJpaEntity();
        entity.setId(appointment.getId());
        entity.setTriageId(appointment.getTriageId());
        entity.setAppointmentTime(appointment.getAppointmentTime());
        entity.setStatus(appointment.getStatus());
        entity.setDoctor(new DoctorJpaEntity(appointment.getDoctor()));

        return entity;

    }
}
