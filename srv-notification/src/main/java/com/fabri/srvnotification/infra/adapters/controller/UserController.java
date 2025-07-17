package com.fabri.srvnotification.infra.adapters.controller;

import com.fabri.srvnotification.application.io.UserEmailInput;
import com.fabri.srvnotification.application.usecase.RegisteredUserMailNotifyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserController {

    private final RegisteredUserMailNotifyUseCase registeredUserMailNotifyUseCase;

    public void notifyRegisteredUser(EmailRequest emailRequest) {
        registeredUserMailNotifyUseCase.execute(new UserEmailInput(
                emailRequest.email(),
                emailRequest.message()
        ));
    }

}
