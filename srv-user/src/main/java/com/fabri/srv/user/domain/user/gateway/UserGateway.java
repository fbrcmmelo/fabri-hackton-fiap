package com.fabri.srv.user.domain.user.gateway;

import com.fabri.srv.user.domain.user.User;

public interface UserGateway {
    User findByUsernameAndPassword(String username, String password);
}
