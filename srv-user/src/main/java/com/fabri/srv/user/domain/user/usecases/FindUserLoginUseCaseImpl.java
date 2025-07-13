package com.fabri.srv.user.domain.user.usecases;


import com.fabri.srv.user.application.FindUserLoginUseCase;
import com.fabri.srv.user.application.dto.UserLoginInput;
import com.fabri.srv.user.application.dto.UserOutput;
import com.fabri.srv.user.domain.user.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindUserLoginUseCaseImpl implements FindUserLoginUseCase {

    private final UserGateway userGateway;

    @Override
    public UserOutput execute(UserLoginInput input) {
        final var encryptedPassword = BCrypt.hashpw(input.password(), BCrypt.gensalt());
        final var user = userGateway.findByUsernameAndPassword(input.username(), encryptedPassword);

        return UserOutput.fromDomain(user);
    }
}
