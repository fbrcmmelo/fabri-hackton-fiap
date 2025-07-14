package com.fabri.srv.user.infra.api;

import com.fabri.srv.user.infra.adapters.controller.UserController;
import com.fabri.srv.user.infra.adapters.controller.dto.AuthRequest;
import com.fabri.srv.user.infra.adapters.controller.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
