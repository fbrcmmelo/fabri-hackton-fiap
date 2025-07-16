package com.fabri.srv.user.domain.user.gateway;

import com.fabri.srv.user.domain.user.User;

import java.util.Optional;

public interface UserGateway {
    User findByUsernameAndPassword(String username, String password);
    User save(User user);
    Optional<User> findByUsername(String username);
    Optional<User> findByCpf(String cpf);
}
