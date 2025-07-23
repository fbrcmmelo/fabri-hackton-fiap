package com.fabri.srv.user.application.dto;

import com.fabri.srv.user.domain.user.Role;
import com.fabri.srv.user.domain.user.User;

import java.time.Instant;
import java.util.stream.Collectors;

public record UserOutput(Long id, String name, String roles, Instant nextAppointmentTime) {
    public static UserOutput fromDomain(User user) {
        return new UserOutput(
                user.getId(),
                user.getFullName().getName(),
                user.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.joining()),
                user.getNextAppointmentTime()
        );
    }

}
