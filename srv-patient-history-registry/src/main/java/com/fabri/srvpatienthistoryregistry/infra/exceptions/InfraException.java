package com.fabri.srvpatienthistoryregistry.infra.exceptions;

public class InfraException extends RuntimeException {
    public InfraException(String message) {
        super("Infrastructure Exception: " + message);
    }
}
