package com.fabri.srvappointment.infra.exception;

public class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }
}
