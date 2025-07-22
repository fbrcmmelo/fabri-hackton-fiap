package com.fabri.srv.user.domain.user.service;

import com.fabri.srv.user.domain.user.User;
import com.fabri.srv.user.domain.user.gateway.UserGateway;
import com.fabri.srv.user.infra.exceptions.UserValidationException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserEmailValidator implements IUserValidation {

    @Override
    public void validate(User user, UserGateway userGateway) throws UserValidationException {
        try {
            userGateway.findByEmail(user.getEmail().getValue());
        } catch (EntityNotFoundException e) {
            // If the email is not found, we can proceed
            return;
        }

        if(userGateway.findByEmail(user.getEmail().getValue()) != null) {
            log.error("Email {} is already in use", user.getEmail().getValue());
            throw new UserValidationException("Email failure");
        }
    }
}
