package com.fabri.srv.user.domain.user.usecases;


import com.fabri.srv.user.application.RegisterPatientUseCase;
import com.fabri.srv.user.application.dto.RegisterUserInput;
import com.fabri.srv.user.application.dto.UserOutput;
import com.fabri.srv.user.domain.IDomainEventPubGateway;
import com.fabri.srv.user.domain.user.User;
import com.fabri.srv.user.domain.user.events.RegisteredUserEvent;
import com.fabri.srv.user.domain.user.service.UserDomainService;
import com.fabri.srv.user.domain.user.vo.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterPatientUseCaseImpl implements RegisterPatientUseCase {

    private final UserDomainService service;
    private final IDomainEventPubGateway domainEventPubGateway;

    @Override
    public UserOutput execute(RegisterUserInput input) {
        var registeredUser = service.registerUser(new User(null, input),
                RoleEnum.PATIENT
        );

        domainEventPubGateway.publish(new RegisteredUserEvent(registeredUser));

        return UserOutput.fromDomain(registeredUser);
    }
}
