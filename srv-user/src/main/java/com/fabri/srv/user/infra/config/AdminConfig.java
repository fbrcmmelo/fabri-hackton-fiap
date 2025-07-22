package com.fabri.srv.user.infra.config;

import com.fabri.srv.user.application.dto.RegisterUserInput;
import com.fabri.srv.user.domain.user.Role;
import com.fabri.srv.user.domain.user.User;
import com.fabri.srv.user.domain.user.gateway.UserGateway;
import com.fabri.srv.user.domain.user.vo.RoleEnum;
import com.fabri.srv.user.infra.persistence.user.RoleJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class AdminConfig {

    private final UserGateway userGateway;
    private final RoleJpaRepository roleJpaRepository;

    @Value("${admin.password}")
    private String admin123;

    @EventListener(ApplicationReadyEvent.class)
    public void initAdmin() {
        var roleAdminOpt = roleJpaRepository.findByName(RoleEnum.ADMIN.name());
        if (roleAdminOpt.isPresent()) {

            try {
               var admin = userGateway.findByEmail("fabri@hackton.com");
               if (admin == null) throw new EntityNotFoundException("Admin not found");
            } catch (EntityNotFoundException e) {
                // If the user already exists, we do nothing
                var admin = RegisterUserInput.builder()
                        .username("admin")
                        .password(BCrypt.hashpw(admin123, BCrypt.gensalt()))
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
