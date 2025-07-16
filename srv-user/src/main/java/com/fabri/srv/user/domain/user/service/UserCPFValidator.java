package com.fabri.srv.user.domain.user.service;

import com.fabri.srv.user.domain.user.User;
import com.fabri.srv.user.domain.user.gateway.UserGateway;
import com.fabri.srv.user.infra.exceptions.UserValidationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserCPFValidator implements IUserValidation {

    @Override
    public void validate(User user, UserGateway userGateway) {
        if (userGateway.findByCpf(user.getCpf().getCpfCnpjSemFormatacao()).isPresent()) {
            log.error("CPF of {} already exists", user.getUsername());
            throw new UserValidationException("CPF");
        }
    }
}
