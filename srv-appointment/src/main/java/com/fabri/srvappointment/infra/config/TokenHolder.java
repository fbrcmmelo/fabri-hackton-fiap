package com.fabri.srvappointment.infra.config;

import org.springframework.stereotype.Component;

@Component
public class TokenHolder {

    private String token;


    public synchronized void setToken(String token) {
        this.token = token;
    }

    public synchronized String getToken() {
        return this.token;
    }

    public synchronized void clear() {
        this.token = null;
    }
}
