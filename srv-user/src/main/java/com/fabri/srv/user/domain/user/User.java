package com.fabri.srv.user.domain.user;

import com.fabri.srv.user.application.dto.RegisterUserInput;
import com.fabri.srv.user.domain.user.vo.Adress;
import com.fabri.srv.user.domain.user.vo.CPF;
import com.fabri.srv.user.domain.user.vo.Email;
import com.fabri.srv.user.domain.user.vo.FullName;
import com.fabri.srv.user.infra.persistence.user.UserJpaEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class User {

    private Long id;
    private String username;
    private String password;
    private FullName fullName;
    private Adress adress;
    private Email email;
    private CPF cpf;
    private Set<Role> roles = Set.of();

    public User(Long id, @NotNull RegisterUserInput input) {
        final var encryptedPassword = BCrypt.hashpw(input.getPassword(), BCrypt.gensalt());

        this.id = id;
        this.username = input.getUsername();
        this.password = encryptedPassword;
        this.fullName = new FullName(input.getFirstName(), input.getLastName());
        this.cpf = new CPF(input.getCpf());
        this.email = new Email(input.getEmail());
        this.adress = new Adress(input.getNumber(), input.getAddress(), input.getCity(), input.getState());
        this.roles = Set.of();
    }

    public User withRoles(@NotNull Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public static User fromJpaEntity(UserJpaEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        final var user = new User();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setPassword(entity.getPassword());
        user.setRoles(entity.getRoles().stream().map(Role::fromJpaEntity).collect(Collectors.toSet()));

        return user;
    }
}
