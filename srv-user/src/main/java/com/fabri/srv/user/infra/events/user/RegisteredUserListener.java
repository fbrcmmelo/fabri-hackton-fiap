package com.fabri.srv.user.infra.events.user;

import com.fabri.srv.user.domain.user.events.RegisteredUserEvent;
import com.fabri.srv.user.infra.utils.EmailNotificationFactory;
import com.fabri.srvmessagebroker.domain.RabbitMqServiceAdapter;
import com.fabri.srvmessagebroker.infra.consts.FilaConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegisteredUserListener {

    private final RabbitMqServiceAdapter rabbitMqServiceAdapter;
    private final EmailNotificationFactory emailNotificationFactory;

    @EventListener
    public void handleUserRegistered(final RegisteredUserEvent event) {
        log.info("User of id: {} and roles: {} has been registered", event.getId(), event.getRoles());
        final var email = emailNotificationFactory.welcome(event.getEmail());
        rabbitMqServiceAdapter.send(FilaConstants.PATIENT_EMAIL_NOTIFICATION, email);
    }

}
