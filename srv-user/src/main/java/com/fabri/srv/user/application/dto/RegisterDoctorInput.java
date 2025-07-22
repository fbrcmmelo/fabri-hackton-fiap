package com.fabri.srv.user.application.dto;

import com.fabri.srv.user.infra.adapters.controller.dto.DoctorRegisterRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class RegisterDoctorInput extends RegisterUserInput {

    private String specialization;
    private String crm;

    public static RegisterDoctorInput from(DoctorRegisterRequest request) {
        return RegisterDoctorInput.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .cpf(request.getCpf())
                .city(request.getCity())
                .state(request.getState())
                .number(request.getNumber())
                .address(request.getAddress())
                .specialization(request.getSpecialization())
                .crm(request.getCrm())
                .build();
    }

}