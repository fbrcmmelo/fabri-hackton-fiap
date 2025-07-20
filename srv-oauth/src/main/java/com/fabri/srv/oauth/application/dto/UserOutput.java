package com.fabri.srv.oauth.application.dto;

import com.fabri.srv.oauth.domain.user.User;

public record UserOutput(Long id, String name, String roles) {
    public static UserOutput fromDomain(User user) {
        return new UserOutput(
                user.getId(),
                user.getName(),
                user.getRoles()
        );
    }
}
