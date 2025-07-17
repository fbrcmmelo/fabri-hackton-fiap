package com.fabri.srvnotification.domain.usecase;

import com.fabri.srvnotification.application.io.UserEmailInput;
import com.fabri.srvnotification.application.usecase.RegisteredUserMailNotifyUseCase;
import com.fabri.srvnotification.domain.Email;
import com.fabri.srvnotification.infra.adapters.SenderMailAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegisteredUserMailNotifyImpl implements RegisteredUserMailNotifyUseCase {

    private final SenderMailAdapter mailSender;

    @Override
    public void execute(UserEmailInput input) {
        Email email = new Email(input.email(),
                "ConectarSaude - Welcome to our service!",
                input.message());

        this.mailSender.sendMail(email);
    }
}
