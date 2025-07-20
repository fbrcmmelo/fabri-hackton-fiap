package com.fabri.srv.oauth.infra.api;

import com.fabri.srv.oauth.infra.adapters.controller.AuthController;
import com.fabri.srv.oauth.infra.adapters.controller.dto.AuthRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationAPI implements AuthenticationOpenAPI {

    private final AuthController authController;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthController.AuthDTO> authenticate(@RequestBody AuthRequest request) {
        log.info("srv-oauth: Received request to authenticate with username {} and password", request.username());
        return ResponseEntity.ok(authController.authenticate(request));
    }

}
