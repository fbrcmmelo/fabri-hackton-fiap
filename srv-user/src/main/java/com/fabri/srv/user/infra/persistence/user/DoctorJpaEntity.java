package com.fabri.srv.user.infra.persistence.user;

import com.fabri.srv.user.domain.user.Doctor;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@DiscriminatorValue("DOCTOR")
public class DoctorJpaEntity extends UserJpaEntity {

    @Column(unique = true)
    private String crm;
    private String specialization;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor")
    private List<DoctorAppointmentJpaEntity> appointments = new ArrayList<>();

    public DoctorJpaEntity(Doctor doctor) {
        super(doctor);
        this.setVersion(doctor.getVersion());
        this.crm = doctor.getCrm().crm();
        this.specialization = doctor.getCrm().specialization();
    }
}
