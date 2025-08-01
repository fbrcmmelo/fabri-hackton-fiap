package com.fabri.srv.user.infra.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {

    Optional<UserJpaEntity> findByUsername(String username);

    Optional<UserJpaEntity> findByCpf(String cpf);

    Optional<DoctorJpaEntity> findByCrm(String crm);

    Optional<UserJpaEntity> findByEmail(String to);

    @Query("SELECT u FROM DoctorJpaEntity u WHERE u.id = ?1 AND (u.crm is not null and u.crm <> '')")
    Optional<DoctorJpaEntity> doctorById(Long doctorId);
}
