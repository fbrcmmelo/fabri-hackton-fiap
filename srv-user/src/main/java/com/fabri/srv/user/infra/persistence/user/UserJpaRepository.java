package com.fabri.srv.user.infra.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
    Optional<UserJpaEntity> findByUsernameAndPassword(String username, String password);

    Optional<UserJpaEntity> findByUsername(String username);

    Optional<UserJpaEntity> findByCpf(String cpf);
}
