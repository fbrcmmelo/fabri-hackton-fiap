package com.fabri.srv.user.infra.adapters.controller;

import com.fabri.srv.user.application.FindUserLoginUseCase;
import com.fabri.srv.user.application.dto.UserLoginInput;
import com.fabri.srv.user.infra.adapters.controller.dto.AuthRequest;
import com.fabri.srv.user.infra.adapters.controller.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserController {

    private final FindUserLoginUseCase findUserUseCase;

    public UserDTO findByUsernameAndPass(AuthRequest request) {
        var userOutput = findUserUseCase.execute(new UserLoginInput(request.username(), request.password()));
        return UserDTO.from(userOutput);
    }

}
