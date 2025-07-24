package com.fabri.srv.user.infra.adapters.controller.dto;

import com.fabri.srv.user.application.dto.UserOutput;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDTO(Long id,
                      String name,
                      String email,
                      String roles,
                      Instant nextAppointmentTime
) {

    public static UserDTO from(UserOutput user) {
        return new UserDTO(
                user.id(),
                user.name(),
                user.email(),
                user.roles(),
                user.nextAppointmentTime()
        );
    }
}
