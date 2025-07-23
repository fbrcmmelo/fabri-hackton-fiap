package com.fabri.srv.user.infra.exceptions;

public class CryptoUtilException extends RuntimeException {

    public CryptoUtilException(String message) {
        super(message);
    }

    public CryptoUtilException(String message, Throwable cause) {
        super(message, cause);
    }
}
