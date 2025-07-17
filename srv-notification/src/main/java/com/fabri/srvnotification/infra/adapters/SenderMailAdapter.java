package com.fabri.srvnotification.infra.adapters;

import com.fabri.srvnotification.domain.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SenderMailAdapter {

    public void sendMail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());

        log.info("############# Sending email #############");
        log.info("Sending email to: {}", email.getTo());
        log.info("Title: {}", email.getSubject());
        log.info("Body: {}", email.getBody());
    }
}
