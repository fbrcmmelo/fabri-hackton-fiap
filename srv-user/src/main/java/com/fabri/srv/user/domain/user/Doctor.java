package com.fabri.srv.user.domain.user;

import com.fabri.srv.user.application.dto.RegisterDoctorInput;
import com.fabri.srv.user.domain.user.vo.Adress;
import com.fabri.srv.user.domain.user.vo.DoctorCRM;
import com.fabri.srv.user.domain.user.vo.Email;
import com.fabri.srv.user.domain.user.vo.FullName;
import com.fabri.srv.user.infra.persistence.user.DoctorJpaEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Doctor extends User {

    private DoctorCRM crm;

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

        Doctor doctor = new Doctor();
        doctor.setId(entity.getId());
        doctor.setUsername(entity.getUsername());
        doctor.setFullName(new FullName(entity.getName()));
        doctor.setEmail(new Email(entity.getEmail()));
        doctor.setCrm(new DoctorCRM(entity.getCrm(), entity.getSpecialization()));
        doctor.setAdress(new Adress(entity.getNumber(), entity.getAddress(), entity.getCity(), entity.getState()));
        doctor.setRoles(entity.getRoles().stream().map(Role::fromJpaEntity).collect(Collectors.toSet()));
        return doctor;
    }

    @Override
    public Doctor withRoles(Set<Role> roles) {
        super.withRoles(roles);
        return this;
    }
}
