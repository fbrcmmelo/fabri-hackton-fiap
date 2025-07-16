package com.fabri.srv.user.infra.adapters.gateway;

public interface IEmailGateway {

    void sendEmail(String email, String message);
}
