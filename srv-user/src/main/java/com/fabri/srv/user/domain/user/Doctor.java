package com.fabri.srv.user.domain.user;

import com.fabri.srv.user.application.dto.RegisterDoctorInput;
import com.fabri.srv.user.domain.user.vo.*;
import com.fabri.srv.user.infra.persistence.user.DoctorJpaEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Doctor extends User {

    private DoctorCRM crm;
    private Instant nextAppointment;

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
        doctor.setPassword(entity.getPassword());
        doctor.setFullName(new FullName(entity.getName()));
        doctor.setEmail(new Email(entity.getEmail()));
        doctor.setCpf(new CPF(entity.getCpf()));
        doctor.setCrm(new DoctorCRM(entity.getCrm(), entity.getSpecialization()));
        doctor.setAdress(new Adress(entity.getNumber(), entity.getAddress(), entity.getCity(), entity.getState()));
        doctor.setRoles(entity.getRoles().stream().map(Role::fromJpaEntity).collect(Collectors.toSet()));
        doctor.setVersion(entity.getVersion());

        return doctor;
    }

    @Override
    public Doctor withRoles(Set<Role> roles) {
        super.withRoles(roles);
        return this;
    }

    public void setNextAppointment(Instant appointmentDate) {
        this.nextAppointment = appointmentDate;
    }
}
