package com.fabri.srvnotification.infra.adapters;

import com.fabri.srvnotification.domain.Email;
import com.fabri.srvnotification.domain.vo.EmailAddress;
import com.fabri.srvnotification.infra.exceptions.EmailSenderException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailSenderAdapter {

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void send(Email email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.getTo().address());
            message.setFrom(fromEmail);
            if (email.getCc() != null && !email.getCc().isEmpty()) {
                message.setCc(email.getCc().stream().map(EmailAddress::address).toArray(String[]::new));
            }
            message.setSubject(email.getTitle().title());
            message.setText(email.getBody().body());

            log.info("############# Sending to #############");
            log.info("Sending to to: {}", email.getTo());
            log.info("From: {}", fromEmail);
            log.info("CC: {}", email.getCc());
            log.info("Title: {}", email.getTitle());
            log.info("Body: {}", email.getBody());
        } catch (Exception e) {
            log.error("Error sending email: {}", e.getMessage(), e);
            throw new EmailSenderException("Failed to send email");
        }
    }
}
