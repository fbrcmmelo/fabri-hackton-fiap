package com.fabri.srv.user.infra.config;

import com.fabri.srv.user.infra.persistence.user.RoleEntity;
import com.fabri.srv.user.infra.persistence.user.RoleJpaRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class RoleConfig {

    private final RoleJpaRepository roleJpaRepository;

    @PostConstruct
    public void initRoles() {
        List<String> roles = List.of("ADMIN", "PATIENT", "DOCTOR", "DOCTOR_PENDING");
        for (String roleName : roles) {
            if (roleJpaRepository.findByName(roleName).isEmpty()) {
                RoleEntity role = new RoleEntity();
                role.setName(roleName);
                roleJpaRepository.save(role);
            }
        }
    }
}