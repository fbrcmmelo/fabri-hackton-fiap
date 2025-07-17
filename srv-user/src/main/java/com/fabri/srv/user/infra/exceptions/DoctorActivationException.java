package com.fabri.srv.user.infra.exceptions;

public class DoctorActivationException extends RuntimeException {
    public DoctorActivationException() {
        super("Activation failed");
    }
}
