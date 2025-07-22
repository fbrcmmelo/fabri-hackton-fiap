package com.fabri.srv.user.domain.user.service;

import com.fabri.srv.user.domain.user.User;
import com.fabri.srv.user.domain.user.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserValidatorHandler {

    private final UserGateway userGateway;
    private final List<IUserValidation> userValidations = List.of(
            new UserCPFValidator(),
            new UsernameValidator(),
            new UserEmailValidator()
    );

    public void validateUserToRegister(User user) {
        userValidations.forEach(validation -> validation.validate(user, userGateway));
    }
}
