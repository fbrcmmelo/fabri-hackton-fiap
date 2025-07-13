package com.fabri.srv.user.infra.api;

import com.fabri.srv.user.infra.adapters.controller.dto.AuthRequest;
import com.fabri.srv.user.infra.adapters.controller.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "User", description = "Operations for user's domain")
public interface UserControllerOpenAPI {

    @Operation(summary = "Find user to login", description = "Finds a user by their username and passwords.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Authentication successful"),
                    @ApiResponse(responseCode = "401", description = "Authentication failed"),
                    @ApiResponse(responseCode = "400", description = "Bad request - invalid input")
            })
    public ResponseEntity<UserDTO> findByUsernameAndPass(AuthRequest request);
}
