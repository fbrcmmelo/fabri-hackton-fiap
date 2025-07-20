package com.fabri.srvnotification.infra.adapters.controller;

import com.fabri.srvnotification.application.io.EmailInput;
import com.fabri.srvnotification.application.usecase.SendEmailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailController {

    private final SendEmailUseCase sendEmailUseCase;

    public void send(EmailRequest emailRequest) {
        EmailInput input = new EmailInput(
                emailRequest.to(),
                emailRequest.cc(),
                emailRequest.title(),
                emailRequest.body()
        );

        sendEmailUseCase.execute(input);
    }

}
