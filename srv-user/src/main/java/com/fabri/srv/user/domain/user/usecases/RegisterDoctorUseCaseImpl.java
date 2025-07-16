package com.fabri.srv.user.domain.user.usecases;


import com.fabri.srv.user.application.RegisterDoctorUseCase;
import com.fabri.srv.user.application.dto.RegisterDoctorInput;
import com.fabri.srv.user.application.dto.UserOutput;
import com.fabri.srv.user.domain.user.Doctor;
import com.fabri.srv.user.domain.user.gateway.RoleGateway;
import com.fabri.srv.user.domain.user.gateway.UserGateway;
import com.fabri.srv.user.domain.user.service.UserValidatorHandler;
import com.fabri.srv.user.domain.user.vo.DoctorCRM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RegisterDoctorUseCaseImpl implements RegisterDoctorUseCase {

    private final UserValidatorHandler validatorHandler;
    private final UserGateway userGateway;
    private final RoleGateway roleGateway;

    @Override
    public UserOutput execute(RegisterDoctorInput input) {
        Objects.requireNonNull(input, "Input cannot be null");

        // Validation crm
        var crm = new DoctorCRM(input.getCrm(), "");
        var doctor = new Doctor(null, input, crm);
        validatorHandler.validateUserToRegister(doctor);


        return UserOutput.fromDomain(doctor);
    }
}
