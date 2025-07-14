package com.fabri.srv.user.infra.adapters.controller;

import com.fabri.srv.user.application.FindUserLoginUseCase;
import com.fabri.srv.user.application.RegisterPatientUseCase;
import com.fabri.srv.user.application.dto.RegisterUserInput;
import com.fabri.srv.user.application.dto.UserLoginInput;
import com.fabri.srv.user.infra.adapters.controller.dto.AuthRequest;
import com.fabri.srv.user.infra.adapters.controller.dto.UserDTO;
import com.fabri.srv.user.infra.adapters.controller.dto.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserController {

    private final FindUserLoginUseCase findUserUseCase;
    private final RegisterPatientUseCase registerPatientUseCase;

    public UserDTO findByUsernameAndPass(AuthRequest request) {
        var userOutput = findUserUseCase.execute(new UserLoginInput(request.username(), request.password()));
        return UserDTO.from(userOutput);
    }

    public UserDTO registerPatient(UserRegisterRequest request) {
        RegisterUserInput registerUserInput = RegisterUserInput.builder()
                .username(request.username())
                .password(request.password())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .cpf(request.cpf())
                .city(request.city())
                .state(request.state())
                .address(request.address())
                .number(request.number())
                .build();

        var userOutput = registerPatientUseCase.execute(registerUserInput);

        return UserDTO.from(userOutput);
    }

}
