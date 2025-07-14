package com.fabri.srv.user.domain.user.service;

import com.fabri.srv.user.domain.user.User;
import com.fabri.srv.user.domain.user.gateway.UserGateway;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserCPFValidator implements IUserValidation {

    @Override
    public void validate(User user, UserGateway userGateway) {
        if (userGateway.findByCpf(user.getCpf().getCpfCnpjSemFormatacao()) != null) {
            log.error("CPF {} already exists", user.getUsername());
            throw new IllegalArgumentException("Inavalid register");
        }
    }
}
