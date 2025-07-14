package com.fabri.srv.user.infra.adapters.controller.dto;

import com.fabri.srv.user.domain.user.Role;

public record RoleDTO(String name) {
    public static RoleDTO from(Role role) {
        return new RoleDTO(role.getName());
    }
}
