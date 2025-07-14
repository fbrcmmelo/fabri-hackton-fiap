package com.fabri.srv.user.domain.user.service;

import com.fabri.srv.user.domain.user.User;
import com.fabri.srv.user.domain.user.gateway.UserGateway;

public interface IUserValidation {

    void validate(User cpf, UserGateway userGateway);
}
