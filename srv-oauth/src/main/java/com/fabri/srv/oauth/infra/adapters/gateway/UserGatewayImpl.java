package com.fabri.srv.oauth.infra.adapters.gateway;

import com.fabri.srv.oauth.domain.user.User;
import com.fabri.srv.oauth.domain.user.gateway.UserGateway;
import com.fabri.srv.oauth.infra.adapters.controller.dto.AuthRequest;
import com.fabri.srv.oauth.infra.clients.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {

    private final UserFeignClient client;

    @Override
    public User findUser(String username, String password) {
        final var request = new AuthRequest(username, BCrypt.hashpw(password, BCrypt.gensalt()));
        return client.loginUser(request).getBody();
    }
}
