package com.fabri.srv.user.infra.events.user;

import com.fabri.srv.user.domain.user.events.ActivatedDoctorEvent;
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
public class ActivatedDoctorListener {

    private final RabbitMqServiceAdapter rabbitMqServiceAdapter;
    private final EmailNotificationFactory emailNotificationFactory;

    @EventListener
    public void handleDoctorActivationEvent(final ActivatedDoctorEvent event) {
        log.info("Doctor activated by user of id: {}", event.getAdminId());
        final var notification = emailNotificationFactory.doctorActivation(event.getEmail());
        rabbitMqServiceAdapter.send(FilaConstants.DOCTOR_EMAIL_NOTIFICATION, notification);
    }
}
