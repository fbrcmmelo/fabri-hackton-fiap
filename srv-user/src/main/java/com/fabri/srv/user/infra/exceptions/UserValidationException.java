package com.fabri.srv.user.infra.exceptions;

public class UserValidationException extends RuntimeException {

    public UserValidationException(String message) {
        super("User validation failed: " + message);
    }
}
