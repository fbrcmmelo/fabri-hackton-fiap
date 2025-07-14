package com.fabri.srv.user.infra.exceptions;

public class DomainException extends RuntimeException {
    public DomainException(String s) {
        super(s);
    }
}
