package com.fabri.srv.oauth.domain.user.usecases;

import com.fabri.srv.oauth.application.FindUserUseCase;
import com.fabri.srv.oauth.application.dto.UserInput;
import com.fabri.srv.oauth.application.dto.UserOutput;
import com.fabri.srv.oauth.domain.user.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindUserUseCaseImpl implements FindUserUseCase {

    private final UserGateway userGateway;

    @Override
    public UserOutput execute(UserInput input) {
        final var encryptedPassword = BCrypt.hashpw(input.password(), BCrypt.gensalt());
        return UserOutput.fromDomain(userGateway.findUser(input.username(), encryptedPassword));
    }
}
