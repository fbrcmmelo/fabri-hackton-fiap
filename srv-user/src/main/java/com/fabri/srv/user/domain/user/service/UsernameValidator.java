package com.fabri.srv.user.domain.user.service;

import com.fabri.srv.user.domain.user.User;
import com.fabri.srv.user.domain.user.gateway.UserGateway;
import com.fabri.srv.user.infra.exceptions.UserValidationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UsernameValidator implements IUserValidation {

    @Override
    public void validate(User user, UserGateway userGateway) {
        if (userGateway.findByUsername(user.getUsername()).isPresent()) {
            log.error("Username {} already exists", user.getUsername());
            throw new UserValidationException("username");
        }
    }
}
