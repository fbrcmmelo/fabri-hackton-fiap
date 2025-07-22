package com.fabri.srv.user.domain.user.service;

import com.fabri.srv.user.domain.user.User;
import com.fabri.srv.user.domain.user.gateway.UserGateway;
import com.fabri.srv.user.infra.exceptions.UserValidationException;

public interface IUserValidation {

    void validate(User user, UserGateway userGateway) throws UserValidationException;
}
