package com.fabri.srv.oauth.infra.adapters.gateway;

import com.fabri.srv.oauth.domain.user.User;
import com.fabri.srv.oauth.domain.user.gateway.UserGateway;
import com.fabri.srv.oauth.infra.adapters.controller.dto.AuthRequest;
import com.fabri.srv.oauth.infra.clients.UserDTO;
import com.fabri.srv.oauth.infra.clients.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {

    private final UserFeignClient client;

    @Override
    public User findUser(String username, String password) {
        UserDTO body = client.loginUser(new AuthRequest(username, password)).getBody();
        if (body == null) {
            throw new IllegalArgumentException("user not found");
        }
        return User.fromDTO(body);
    }
}
