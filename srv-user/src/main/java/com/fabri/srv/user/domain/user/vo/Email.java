package com.fabri.srv.user.domain.user.vo;

import lombok.Getter;

@Getter
public class Email {

    @jakarta.validation.constraints.Email
    private String value;

    public Email(String value) {
        this.value = value;
    }
}
