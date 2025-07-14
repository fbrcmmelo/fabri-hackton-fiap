package com.fabri.srv.oauth.domain.user.gateway;

import com.fabri.srv.oauth.domain.user.User;

public interface UserGateway {
    User findUser(String username, String password);
}
