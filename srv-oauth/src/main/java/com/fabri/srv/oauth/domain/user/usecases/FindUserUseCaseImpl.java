package com.fabri.srv.oauth.domain.user.usecases;

import com.fabri.srv.oauth.application.FindUserUseCase;
import com.fabri.srv.oauth.application.dto.UserInput;
import com.fabri.srv.oauth.application.dto.UserOutput;
import com.fabri.srv.oauth.domain.user.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindUserUseCaseImpl implements FindUserUseCase {

    private final UserGateway userGateway;

    @Override
    public UserOutput execute(UserInput input) {
        final var encryptedPassword = BCrypt.hashpw(input.password(), BCrypt.gensalt());

        return Optional.ofNullable(userGateway.findUser(input.username(), encryptedPassword))
                .map(UserOutput::fromDomain)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
