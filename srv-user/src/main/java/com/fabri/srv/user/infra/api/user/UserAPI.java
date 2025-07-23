package com.fabri.srv.user.infra.api.user;

import com.fabri.srv.user.infra.adapters.controller.UserController;
import com.fabri.srv.user.infra.adapters.controller.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users", produces = "application/json")
public class UserAPI implements UserControllerOpenAPI {

    private final UserController userController;

    @Override
    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<UserDTO> findByUsernameAndPass(@RequestBody AuthRequest request) {
        log.info("srv-user: Received request to find user by username {} and password", request.username());
        return ResponseEntity.ok(userController.findByUsernameAndPass(request));
    }

    @Override
    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserRegisterRequest request) {
        log.info("srv-user: Received request to register user");
        return ResponseEntity.ok(userController.registerPatient(request));
    }

    @Override
    @PostMapping(value = "/doctors/register", consumes = "application/json")
    public ResponseEntity<UserDTO> registerDoctor(@RequestBody DoctorRegisterRequest request) {
        log.info("srv-user: Received request to register doctor");
        return ResponseEntity.ok(userController.registerDoctor(request));
    }

    @Override
    @PostMapping(value = "/doctors/activate", consumes = "application/json")
    public ResponseEntity<UserDTO> activateDoctor(@RequestBody ActivateDoctorRequest request) {
        log.info("srv-user: Received request to activate doctor");
        return ResponseEntity.ok(userController.activateDoctor(request));
    }

    @Override
    @GetMapping(value = "/{userId}", produces = "application/json")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String userId) {
        log.info("srv-user: Received request to get user by id: {}", userId);
        return ResponseEntity.ok(userController.getUserById(userId));
    }

    @Override
    @PutMapping(value = "/doctors/{doctorId}/next-appointment", consumes = "application/json")
    public ResponseEntity<Void> saveNextDoctorAppointment(@PathVariable Long doctorId,
                                                          @RequestBody SaveNextDoctorAppointmentRequest request) {
       log.info("srv-user: Received request to save next appointment for doctor with id: {}", doctorId);
        userController.saveNextDoctorAppointment(doctorId, request);
        return ResponseEntity.ok().build();
    }

}
