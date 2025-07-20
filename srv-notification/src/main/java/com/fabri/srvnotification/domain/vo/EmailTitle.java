package com.fabri.srvnotification.domain.vo;

public record EmailTitle(String title) {

    public EmailTitle {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
    }
}
