package com.fabri.srv.user.domain.user;

import com.fabri.srv.user.infra.persistence.user.RoleEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@SuppressWarnings("serial")
public class Role implements Serializable {
    private Long id;
    private String name;

    public static Role fromEntity(RoleEntity entity) {
        Role role = new Role();
        role.setId(entity.getId());
        role.setName(entity.getName());

        return role;
    }
}
