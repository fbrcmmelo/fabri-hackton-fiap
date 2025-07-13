package com.fabri.srv.user.infra.adapters.controller.dto;

import com.fabri.srv.user.application.dto.UserOutput;

import java.util.Set;
import java.util.stream.Collectors;

public record UserDTO(Long id, Set<RoleDTO> roles) {

    public static UserDTO from(UserOutput user) {
        return new UserDTO(
                user.id(),
                user.roles().stream()
                        .map(RoleDTO::from)
                        .collect(Collectors.toSet()));
    }
}
