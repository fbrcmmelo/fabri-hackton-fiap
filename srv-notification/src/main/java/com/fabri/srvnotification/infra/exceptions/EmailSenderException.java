package com.fabri.srvnotification.infra.exceptions;

public class EmailSenderException extends RuntimeException {

    public EmailSenderException(String address) {
        super("Invalid address address: " + address);
    }
}
