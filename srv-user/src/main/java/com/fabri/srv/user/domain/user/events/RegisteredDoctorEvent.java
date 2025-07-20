package com.fabri.srv.user.domain.user.events;

import com.fabri.srv.user.domain.IDomainEvent;
import com.fabri.srv.user.domain.user.Doctor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@Getter
public class RegisteredDoctorEvent implements IDomainEvent {

    private final Long id;
    private final String crm;
    private final String name;
    private final String email;
    private final String cpf;

    public RegisteredDoctorEvent(Doctor doctor) {
        Objects.requireNonNull(doctor);

        this.id = doctor.getId();
        this.cpf = doctor.getCpf().getCpfCnpj();
        this.email = doctor.getEmail().getValue();
        this.crm = doctor.getCrm().crm();
        this.name = doctor.getFullName().getName();
    }

    @Override
    public String toString() {
        return "Doctor registered";
    }
}
