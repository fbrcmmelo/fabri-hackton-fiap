package com.fabri.srv.user.domain.user;

import com.fabri.srv.user.application.dto.RegisterDoctorInput;
import com.fabri.srv.user.domain.user.vo.DoctorCRM;
import com.fabri.srv.user.infra.persistence.user.DoctorJpaEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Doctor extends User {

    DoctorCRM crm;

    public Doctor(Long id, RegisterDoctorInput input, DoctorCRM crm) {
        super(id, input);
        Objects.requireNonNull(input, "Input cannot be null");
        Objects.requireNonNull(crm, "CRM cannot be null");

        this.crm = crm;
    }

    public static Doctor fromJpaEntity(DoctorJpaEntity entity) {
        if (entity == null) {
            return null;
        }

        Doctor doctor = (Doctor) User.fromJpaEntity(entity);
        Objects.requireNonNull(doctor, "Doctor cannot be null from Jpa");
        doctor.setCrm(new DoctorCRM(entity.getCrm(), entity.getSpecialization()));

        return doctor;
    }

    @Override
    public Doctor withRoles(Set<Role> roles) {
        super.withRoles(roles);
        return this;
    }
}
