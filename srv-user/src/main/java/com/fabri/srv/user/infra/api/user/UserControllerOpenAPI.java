package com.fabri.srv.user.infra.api.user;

import com.fabri.srv.user.infra.adapters.controller.dto.*;
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
    ResponseEntity<UserDTO> findByUsernameAndPass(AuthRequest request);

    @Operation(summary = "Register a new user", description = "Registers a new user in the system.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User registered successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad request - invalid input"),
                    @ApiResponse(responseCode = "409", description = "Conflict - user already exists")
            })
    ResponseEntity<UserDTO> registerUser(UserRegisterRequest request);

    @Operation(summary = "Register a new doctor", description = "Registers a new doctor in the system.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Doctor registered successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad request - invalid input"),
                    @ApiResponse(responseCode = "409", description = "Conflict - doctor already exists")
            })
    ResponseEntity<UserDTO> registerDoctor(DoctorRegisterRequest request);

    @Operation(summary = "Activate a doctor", description = "Activates a doctor in the system.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Doctor activated successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad request - invalid input"),
                    @ApiResponse(responseCode = "404", description = "Not found - doctor or activator not found")
            })
    ResponseEntity<UserDTO> activateDoctor(ActivateDoctorRequest request);
}
