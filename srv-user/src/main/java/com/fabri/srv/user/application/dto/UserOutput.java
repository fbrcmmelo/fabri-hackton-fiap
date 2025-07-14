package com.fabri.srv.user.application.dto;

import com.fabri.srv.user.domain.user.Role;
import com.fabri.srv.user.domain.user.User;

import java.util.Set;

public record UserOutput(Long id, String email, Set<Role> roles) {
    public static UserOutput fromDomain(User user) {
        return new UserOutput(
                user.getId(),
                user.getEmail(),
                user.getRoles()
        );
    }
}
