package com.fabri.srv.user.infra.persistence.user;

import com.fabri.srv.user.domain.user.Doctor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DoctorJpaEntity extends UserJpaEntity {

    @Column(unique = true)
    private String crm;
    private String specialization;

    public DoctorJpaEntity(Doctor doctor) {
        super(doctor);
        this.crm = doctor.getCrm().crm();
        this.specialization = doctor.getCrm().specialization();
    }
}
