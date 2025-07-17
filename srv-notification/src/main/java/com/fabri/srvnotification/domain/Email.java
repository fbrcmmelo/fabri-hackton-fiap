package com.fabri.srvnotification.domain;

import lombok.Getter;

@Getter
public class Email {

    private String to;
    private String subject;
    private String body;

    public Email(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }
}
