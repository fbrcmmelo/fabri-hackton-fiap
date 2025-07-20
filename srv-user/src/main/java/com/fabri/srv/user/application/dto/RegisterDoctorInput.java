package com.fabri.srv.user.application.dto;

import com.fabri.srv.user.infra.adapters.controller.dto.DoctorRegisterRequest;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterDoctorInput extends RegisterUserInput {

    private String specialization;
    private String crm;

    public static RegisterDoctorInput from(DoctorRegisterRequest request) {
        RegisterDoctorInput doctorInput = (RegisterDoctorInput) RegisterUserInput.from(request);
        doctorInput.setCrm(request.getCrm());
        doctorInput.setSpecialization(request.getSpecialization());

        return doctorInput;
    }

}