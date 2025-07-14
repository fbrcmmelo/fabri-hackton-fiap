package com.fabri.srv.oauth.infra.adapters.controller;

import com.fabri.srv.oauth.application.AuthenticationUseCase;
import com.fabri.srv.oauth.application.FindUserUseCase;
import com.fabri.srv.oauth.application.dto.AuthOutput;
import com.fabri.srv.oauth.application.dto.UserInput;
import com.fabri.srv.oauth.infra.adapters.controller.dto.AuthRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class AuthController {

    private final FindUserUseCase findUserUseCase;
    private final AuthenticationUseCase authenticationUseCase;

    public AuthDTO authenticate(AuthRequest request) {
        var userOutput = findUserUseCase.execute(new UserInput(request.username(), request.password()));
        var authOutput = authenticationUseCase.execute(userOutput);

        return AuthDTO.from(authOutput);
    }

    public record AuthDTO(String accessToken, String refreshToken, Instant expiresAt) {
        static AuthDTO from(AuthOutput authOutput) {
            return new AuthDTO(
                    authOutput.accessToken(),
                    authOutput.refreshToken(),
                    authOutput.expiresAt()
            );
        }
    }
}
