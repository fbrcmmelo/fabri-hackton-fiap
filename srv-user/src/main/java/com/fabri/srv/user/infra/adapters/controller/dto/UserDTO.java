package com.fabri.srv.user.infra.adapters.controller.dto;

import com.fabri.srv.user.application.dto.UserOutput;

public record UserDTO(Long id) {

    public static UserDTO from(UserOutput user) {
        return new UserDTO(user.id());
    }
}
