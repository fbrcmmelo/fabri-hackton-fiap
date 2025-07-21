package com.fabri.srvappointment.infra.events;

import com.fabri.srvappointment.domain.event.FinishedAppointmentEvent;
import com.fabri.srvappointment.infra.utils.EmailNotificationFactory;
import com.fabri.srvmessagebroker.domain.RabbitMqServiceAdapter;
import com.fabri.srvmessagebroker.infra.consts.FilaConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FinishedAppointmentListener {

    private final RabbitMqServiceAdapter rabbitMqServiceAdapter;

    @EventListener
    public void handleScheduledAppointmentEvent(final FinishedAppointmentEvent event) {
        log.info("Received finished appointment event");

        log.info("Sending appointment info to EMR System");
        rabbitMqServiceAdapter.send(FilaConstants.FINISHED_PATIENT_APPOINTMENT, event.getAppointment());

        log.info("Sending message to Patient Notification");
        var finishedPatientAppointment = new EmailNotificationFactory().finishedPatientAppointment(event);
        rabbitMqServiceAdapter.send(FilaConstants.PATIENT_EMAIL_NOTIFICATION, finishedPatientAppointment);
    }
}
