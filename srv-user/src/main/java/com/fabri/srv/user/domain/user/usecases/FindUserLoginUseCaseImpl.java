package com.fabri.srv.user.domain.user.usecases;

import com.fabri.srv.user.application.FindUserLoginUseCase;
import com.fabri.srv.user.application.dto.UserLoginInput;
import com.fabri.srv.user.application.dto.UserOutput;
import com.fabri.srv.user.domain.user.gateway.UserGateway;
import com.fabri.srv.user.infra.exceptions.DomainException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindUserLoginUseCaseImpl implements FindUserLoginUseCase {

    private final UserGateway userGateway;

    @Override
    public UserOutput execute(UserLoginInput input) {
        final var user = userGateway.findByUsername(input.username()).orElseThrow(EntityNotFoundException::new);
        boolean checked = BCrypt.checkpw(input.password(), user.getPassword());

        if (!checked) {
            log.warn(String.format("Username %s and password check failed", input.username()));
            throw new DomainException("Invalid username or password");
        }

        return UserOutput.fromDomain(user);
    }
}
