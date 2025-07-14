package com.fabri.srv.user.infra.persistence.user;

import com.fabri.srv.user.domain.user.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@SuppressWarnings("serial")
@Table(name = "tb_role")
public class RoleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<UserJpaEntity> users = new HashSet<>();

    public RoleEntity(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }
}
