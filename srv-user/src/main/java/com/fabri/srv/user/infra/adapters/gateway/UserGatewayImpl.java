package com.fabri.srv.user.infra.adapters.gateway;

import com.fabri.srv.user.domain.user.User;
import com.fabri.srv.user.domain.user.gateway.UserGateway;
import com.fabri.srv.user.infra.persistence.user.UserJpaEntity;
import com.fabri.srv.user.infra.persistence.user.UserJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {

    private final UserJpaRepository jpaRepository;

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        final var entity = jpaRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return User.fromJpaEntity(entity);
    }

    @Override
    public User save(User user) {
        return User.fromJpaEntity(jpaRepository.save(new UserJpaEntity(user)));
    }

    @Override
    public User findByUsername(String username) {
        return jpaRepository.findByUsername(username)
                .map(User::fromJpaEntity)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User findByCpf(String cpf) {
        return jpaRepository.findByCpf(cpf)
                .map(User::fromJpaEntity)
                .orElseThrow(EntityNotFoundException::new);
    }
}
