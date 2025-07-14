package com.fabri.srv.oauth.infra.api;

import com.fabri.srv.oauth.infra.adapters.controller.AuthController;
import com.fabri.srv.oauth.infra.adapters.controller.dto.AuthRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

@Tag(name = "Authentication", description = "Authentication API")
public interface AuthenticationOpenAPI {

    @Operation(summary = "Authenticate user", description = "Authenticate a user with username and password",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Authentication successful"),
                    @ApiResponse(responseCode = "401", description = "Authentication failed"),
                    @ApiResponse(responseCode = "400", description = "Bad request - invalid input")
            })
    ResponseEntity<AuthController.AuthDTO> authenticate(
            @Schema(implementation = AuthRequest.class, requiredMode = Schema.RequiredMode.REQUIRED)
            AuthRequest request);

    @Operation(summary = "OAuth2 Token Endpoint",
            description = "Endpoint for OAuth2 token generation and management",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Token generated successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized - invalid credentials")
            })
    ResponseEntity<AuthController.AuthDTO> oauth2TokenEndpoint(OidcUser user);
}
