package com.fabri.srv.user.domain.user.vo;

import lombok.Getter;

@Getter
public class FullName {
    private final String name;

    public FullName(String firstName, String lastName) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be null or blank");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be null or blank");
        }

        this.name = firstName + " " + lastName;
    }

    public FullName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Full name cannot be null or blank");
        }

        this.name = name;
    }

}
