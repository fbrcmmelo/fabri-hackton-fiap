package com.fabri.srv.user.infra.adapters.gateway;

import com.fabri.srv.user.domain.user.User;
import com.fabri.srv.user.domain.user.gateway.UserGateway;
import com.fabri.srv.user.infra.persistence.user.UserJpaEntity;
import com.fabri.srv.user.infra.persistence.user.UserJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {

    private final UserJpaRepository jpaRepository;

    @Override
    public User save(User user) {
        return User.fromJpaEntity(jpaRepository.save(new UserJpaEntity(user)));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        var byUsername = jpaRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
        return Optional.ofNullable(User.fromJpaEntity(byUsername));
    }

    @Override
    public Optional<User> findByCpf(String cpf) {
        return jpaRepository.findByCpf(cpf).map(User::fromJpaEntity);
    }

    @Override
    public User findByEmail(String to) {
        final var byEmail = jpaRepository.findByEmail(to).orElseThrow(EntityNotFoundException::new);
        return User.fromJpaEntity(byEmail);
    }

    @Override
    public User findById(Long input) {
        final var byId = jpaRepository.findById(input).orElseThrow(EntityNotFoundException::new);
        return User.fromJpaEntity(byId);
    }
}
