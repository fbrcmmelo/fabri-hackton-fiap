package com.fabri.srv.user.infra.adapters.controller.dto;

import com.fabri.srv.user.application.dto.UserOutput;

import java.time.Instant;

public record UserDTO(Long id,
                      String name,
                      String roles,
                      Instant nextAppointmentTime
) {

    public static UserDTO from(UserOutput user) {
        return new UserDTO(
                user.id(),
                user.name(),
                user.roles(),
                user.nextAppointmentTime()
        );
    }
}
