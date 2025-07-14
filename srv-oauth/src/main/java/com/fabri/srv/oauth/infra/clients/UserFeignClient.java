package com.fabri.srv.oauth.infra.clients;

import com.fabri.srv.oauth.domain.user.User;
import com.fabri.srv.oauth.infra.adapters.controller.dto.AuthRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "srv-user", path = "/users")
public interface UserFeignClient {

    @PostMapping("/login")
    ResponseEntity<User> loginUser(@RequestBody AuthRequest request);
}
