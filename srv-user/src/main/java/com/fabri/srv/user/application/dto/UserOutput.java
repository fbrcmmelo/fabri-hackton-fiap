package com.fabri.srv.user.application.dto;

import com.fabri.srv.user.domain.user.User;

public record UserOutput(Long id) {
    public static UserOutput fromDomain(User user) {
        return new UserOutput(
                user.getId()
        );
    }
}
