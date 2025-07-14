package com.fabri.srv.user.domain.user.vo;

public record FullName(String firstName, String lastName) {

    public FullName {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be null or blank");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be null or blank");
        }
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
