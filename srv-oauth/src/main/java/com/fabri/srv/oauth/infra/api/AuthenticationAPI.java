package com.fabri.srv.oauth.infra.api;

import com.fabri.srv.oauth.infra.adapters.controller.AuthController;
import com.fabri.srv.oauth.infra.adapters.controller.dto.AuthRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationAPI implements AuthenticationOpenAPI {

    private final AuthController authController;

    @PostMapping(value = "/auth", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthController.AuthDTO> authenticate(@RequestBody AuthRequest request) {
        log.info("srv-oauth: Received request to authenticate with username {} and password", request.username());
        return ResponseEntity.ok(authController.authenticate(request));
    }

    @Override
    @GetMapping("/oauth2")
    public ResponseEntity<AuthController.AuthDTO> oauth2TokenEndpoint(@AuthenticationPrincipal OidcUser user) {
        log.info("srv-oauth: Getting oauth2 token endpoint for user {}", user.getName());
        OidcIdToken idToken = user.getIdToken();

        AuthController.AuthDTO authDTO = new AuthController.AuthDTO(
                idToken.getTokenValue(),
                "",
                idToken.getExpiresAt()
        );

        return ResponseEntity.ok(authDTO);
    }
}
