package com.fabri.srvnotification.domain.usecase;

import com.fabri.srvnotification.application.io.EmailInput;
import com.fabri.srvnotification.application.usecase.SendEmailUseCase;
import com.fabri.srvnotification.domain.Email;
import com.fabri.srvnotification.infra.adapters.EmailSenderAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendEmailUseCaseImpl implements SendEmailUseCase {

    private final EmailSenderAdapter mailService;

    @Override
    public void execute(EmailInput input) {
        if (input.to() == null || input.to().isBlank()) {
            log.error("Email 'to' address is required");
            throw new IllegalArgumentException("Email 'to' address cannot be null or blank");
        }

        final var email = new Email(input.to(), input.cc(), input.title(), input.message());
        mailService.send(email);
    }
}
