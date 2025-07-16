package com.fabri.srv.user.application.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class RegisterDoctorInput extends RegisterUserInput {

    private String crm;

}