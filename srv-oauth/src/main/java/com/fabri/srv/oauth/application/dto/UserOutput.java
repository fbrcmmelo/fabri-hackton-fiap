package com.fabri.srv.oauth.application.dto;

import com.fabri.srv.oauth.domain.user.Role;
import com.fabri.srv.oauth.domain.user.User;

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
