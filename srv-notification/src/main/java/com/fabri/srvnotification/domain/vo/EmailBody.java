package com.fabri.srvnotification.domain.vo;

public record EmailBody(String body) {

    public EmailBody {
        if (body == null || body.isBlank()) {
            throw new IllegalArgumentException("Body cannot be null or blank");
        }
    }
}
