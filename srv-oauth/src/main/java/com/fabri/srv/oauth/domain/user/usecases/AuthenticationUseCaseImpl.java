package com.fabri.srv.oauth.domain.user.usecases;

import com.fabri.srv.oauth.application.AuthenticationUseCase;
import com.fabri.srv.oauth.application.dto.AuthOutput;
import com.fabri.srv.oauth.application.dto.UserOutput;
import com.fabri.srv.oauth.infra.utils.JWTType;
import com.fabri.srv.oauth.infra.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class AuthenticationUseCaseImpl implements AuthenticationUseCase {

    private final JWTUtils jwtUtils;

    @Override
    public AuthOutput execute(UserOutput user) {
        String accessToken = jwtUtils.generateToken(String.valueOf(user.id()), user.roles(), JWTType.ACESS_TOKEN);
        String refreshToken = jwtUtils.generateToken(String.valueOf(user.id()), user.roles(), JWTType.REFRESH_TOKEN);
        Instant exp = jwtUtils.getExpirationDateFromToken(accessToken);

        return new AuthOutput(accessToken, refreshToken, exp);
    }
}
