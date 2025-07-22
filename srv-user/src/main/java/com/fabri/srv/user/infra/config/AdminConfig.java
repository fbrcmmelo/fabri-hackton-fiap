package com.fabri.srv.user.infra.config;

import com.fabri.srv.user.application.dto.RegisterUserInput;
import com.fabri.srv.user.domain.user.Role;
import com.fabri.srv.user.domain.user.User;
import com.fabri.srv.user.domain.user.gateway.UserGateway;
import com.fabri.srv.user.domain.user.vo.RoleEnum;
import com.fabri.srv.user.infra.persistence.user.RoleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.Set;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class AdminConfig {

    private final UserGateway userGateway;
    private final RoleJpaRepository roleJpaRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initAdmin() {
        var roleAdminOpt = roleJpaRepository.findByName(RoleEnum.ADMIN.name());
        if (roleAdminOpt.isPresent()) {

            if (userGateway.findByEmail("fabri@hackton.com") == null) {
                var admin = RegisterUserInput.builder()
                        .username("admin")
                        .password(BCrypt.hashpw(UUID.randomUUID().toString(), BCrypt.gensalt()))
                        .email("fabri@hackton.com")
                        .address("Rua Admin")
                        .number(123)
                        .city("Admin City")
                        .state("Admin State")
                        .firstName("Admin")
                        .lastName("User")
                        .cpf("12345678901")
                        .build();

                Role roleAdmin = new Role(roleAdminOpt.get().getId(), RoleEnum.ADMIN.name());
                this.userGateway.save(new User(null, admin).withRoles(Set.of(roleAdmin)));
            }
        }
    }
}
