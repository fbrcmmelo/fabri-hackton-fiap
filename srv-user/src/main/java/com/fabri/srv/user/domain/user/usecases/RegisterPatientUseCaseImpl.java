package com.fabri.srv.user.domain.user.usecases;


import com.fabri.srv.user.application.RegisterPatientUseCase;
import com.fabri.srv.user.application.dto.RegisterUserInput;
import com.fabri.srv.user.application.dto.UserOutput;
import com.fabri.srv.user.domain.user.User;
import com.fabri.srv.user.domain.user.gateway.RoleGateway;
import com.fabri.srv.user.domain.user.gateway.UserGateway;
import com.fabri.srv.user.domain.user.service.UserValidatorHandler;
import com.fabri.srv.user.domain.user.vo.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RegisterPatientUseCaseImpl implements RegisterPatientUseCase {

    private final UserValidatorHandler validatorHandler;
    private final UserGateway userGateway;
    private final RoleGateway roleGateway;

    @Override
    public UserOutput execute(RegisterUserInput input) {
        final var patientRole = roleGateway.byEnum(RoleEnum.PATIENTE);
        Objects.requireNonNull(patientRole, "Patient role not found");

        final var user = new User(null, input).withRoles(Set.of(patientRole));

        validatorHandler.validateUserToRegister(user);

        return UserOutput.fromDomain(userGateway.save(user));
    }
}
