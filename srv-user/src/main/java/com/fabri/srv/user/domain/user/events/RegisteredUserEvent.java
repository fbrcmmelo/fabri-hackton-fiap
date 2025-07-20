package com.fabri.srv.user.domain.user.events;

import com.fabri.srv.user.domain.IDomainEvent;
import com.fabri.srv.user.domain.user.User;
import lombok.Getter;

import java.util.Objects;

@Getter
public class RegisteredUserEvent implements IDomainEvent {

    private String id;
    private String name;
    private String email;
    private String roles;

    public RegisteredUserEvent(User user) {
        Objects.requireNonNull(user, "user is required");

        this.id = String.valueOf(user.getId());
        this.name = user.getFullName().getName();
        this.email = user.getEmail().getValue();
        this.roles = user.rolesAsString();
    }

    @Override
    public String toString() {
        return "User registered";
    }
}
