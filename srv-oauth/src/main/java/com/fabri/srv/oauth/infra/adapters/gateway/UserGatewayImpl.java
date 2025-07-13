package com.fabri.srv.oauth.infra.adapters.gateway;

import com.fabri.srv.oauth.domain.user.User;
import com.fabri.srv.oauth.domain.user.gateway.UserGateway;
import com.fabri.srv.oauth.infra.clients.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {

    private final UserFeignClient client;

    @Override
    public User findUser(String username, String password) {
        return client.findByEmail(username).getBody();
    }
}
