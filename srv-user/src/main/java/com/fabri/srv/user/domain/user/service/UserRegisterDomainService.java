package com.fabri.srv.user.domain.user.service;

import com.fabri.srv.user.domain.IDomainEventPubGateway;
import com.fabri.srv.user.domain.user.User;
import com.fabri.srv.user.domain.user.events.RegisteredUserEvent;
import com.fabri.srv.user.domain.user.gateway.UserGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackOn =  Exception.class)
@RequiredArgsConstructor
public class UserRegisterDomainService {

    private final UserGateway userGateway;
    private final IDomainEventPubGateway domainEventPubGateway;
    private final UserValidatorHandler userValidatorHandler;

    public User registerUser(User user) {
        userValidatorHandler.validateUserToRegister(user);
        var registeredUser = userGateway.save(user);
        domainEventPubGateway.publish(new RegisteredUserEvent(registeredUser));

        return  registeredUser;
    }
}
