package com.fabri.srv.user.domain.user;

import com.fabri.srv.user.infra.persistence.user.UserJpaEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Set<Role> roles;

    public User(Long id, String name, String email, String password) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static User fromJpaEntity(UserJpaEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setName(entity.getUsername());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setRoles(entity.getRoles().stream()
                .map(Role::fromEntity)
                .collect(Collectors.toSet()));
        return user;
    }
}
