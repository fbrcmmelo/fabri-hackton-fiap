package com.fabri.srv.user.infra.adapters.controller;

import com.fabri.srv.user.application.ActivateDoctorUseCase;
import com.fabri.srv.user.application.FindUserLoginUseCase;
import com.fabri.srv.user.application.RegisterDoctorUseCase;
import com.fabri.srv.user.application.RegisterPatientUseCase;
import com.fabri.srv.user.application.dto.ActivateDoctorInput;
import com.fabri.srv.user.application.dto.RegisterDoctorInput;
import com.fabri.srv.user.application.dto.RegisterUserInput;
import com.fabri.srv.user.application.dto.UserLoginInput;
import com.fabri.srv.user.infra.adapters.controller.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserController {

    private final FindUserLoginUseCase findUserUseCase;
    private final RegisterPatientUseCase registerPatientUseCase;
    private final RegisterDoctorUseCase registerDoctorUseCase;
    private final ActivateDoctorUseCase activateDoctorUseCase;

    public UserDTO findByUsernameAndPass(AuthRequest request) {
        var input = new UserLoginInput(request.username(), request.password());
        var output = findUserUseCase.execute(input);

        return UserDTO.from(output);
    }

    public UserDTO registerPatient(UserRegisterRequest request) {
        var input = RegisterUserInput.from(request);
        var output = registerPatientUseCase.execute(input);

        return UserDTO.from(output);
    }

    public UserDTO registerDoctor(DoctorRegisterRequest request) {
        var input = RegisterDoctorInput.from(request);
        var output = registerDoctorUseCase.execute(input);

        return UserDTO.from(output);
    }

    public UserDTO activateDoctor(ActivateDoctorRequest request) {
        var input = ActivateDoctorInput.fromRequest(request);
        var output = activateDoctorUseCase.execute(input);

        return UserDTO.from(output);
    }

}
