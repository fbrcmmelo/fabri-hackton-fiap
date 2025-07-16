package com.fabri.srv.user.domain.user.usecases;


import com.fabri.srv.user.application.RegisterPatientUseCase;
import com.fabri.srv.user.application.dto.RegisterUserInput;
import com.fabri.srv.user.application.dto.UserOutput;
import com.fabri.srv.user.domain.user.User;
import com.fabri.srv.user.domain.user.gateway.RoleGateway;
import com.fabri.srv.user.domain.user.service.UserRegisterDomainService;
import com.fabri.srv.user.domain.user.vo.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RegisterPatientUseCaseImpl implements RegisterPatientUseCase {

    private final RoleGateway roleGateway;
    private final UserRegisterDomainService service;

    @Override
    public UserOutput execute(RegisterUserInput input) {
        final var patientRole = roleGateway.byEnum(RoleEnum.PATIENT);
        Objects.requireNonNull(patientRole, "Patient role not found");
        final var user = new User(null, input).withRoles(Set.of(patientRole));

        service.registerUser(user);

        return UserOutput.fromDomain(user);
    }
}
