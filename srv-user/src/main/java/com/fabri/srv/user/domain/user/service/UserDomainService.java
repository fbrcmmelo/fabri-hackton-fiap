package com.fabri.srv.user.domain.user.service;

import com.fabri.srv.user.domain.IDomainEventPubGateway;
import com.fabri.srv.user.domain.user.User;
import com.fabri.srv.user.domain.user.events.RegisteredUserEvent;
import com.fabri.srv.user.domain.user.gateway.RoleGateway;
import com.fabri.srv.user.domain.user.gateway.UserGateway;
import com.fabri.srv.user.domain.user.vo.RoleEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Transactional(rollbackOn =  Exception.class)
@RequiredArgsConstructor
public class UserDomainService {

    private final UserGateway userGateway;
    private final RoleGateway roleGateway;
    private final UserValidatorHandler userValidatorHandler;
    private final IDomainEventPubGateway domainEventPubGateway;

    public User registerUser(User user, RoleEnum role) {
        userValidatorHandler.validateUserToRegister(user);
        User registeredUser = userGateway.save(user.withRoles(Set.of(roleGateway.byEnum(role))));
        domainEventPubGateway.publish(new RegisteredUserEvent(registeredUser));

        return registeredUser;
    }
}
