package com.fabri.srv.user.infra.exceptions;

public class DoctorActivationException extends DomainException {
    public DoctorActivationException() {
        super("Activation failed");
    }
}
