package com.fabri.srv.user.infra.events.user;

import com.fabri.srv.user.domain.user.events.RegisteredDoctorEvent;
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
public class RegisteredDoctorListener {

    private final RabbitMqServiceAdapter rabbitMqServiceAdapter;
    private final EmailNotificationFactory emailNotificationFactory;

    @EventListener
    public void handleDoctorRegisteredEvent(final RegisteredDoctorEvent event) {
        log.info("Doctor of id: {} has been registered", event.getId());
        final var notification = emailNotificationFactory.doctorWelcome(event.getEmail());
        rabbitMqServiceAdapter.send(FilaConstants.DOCTOR_EMAIL_NOTIFICATION, notification);
    }
}
