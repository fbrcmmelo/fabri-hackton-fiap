package com.fabri.srv.user.domain.user.usecases;

import com.fabri.srv.user.application.FindUserLoginUseCase;
import com.fabri.srv.user.application.dto.UserLoginInput;
import com.fabri.srv.user.application.dto.UserOutput;
import com.fabri.srv.user.domain.user.gateway.UserGateway;
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
        final var encryptedPassword = BCrypt.hashpw(input.password(), BCrypt.gensalt());
        boolean checked = BCrypt.checkpw(input.password(), encryptedPassword);

        if (!checked) {
            log.warn(String.format("Username %s and password check failed", input.username()));
            throw new EntityNotFoundException();
        }

        return UserOutput.fromDomain(user);
    }
}
