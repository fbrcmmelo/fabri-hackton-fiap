package com.fabri.srvappointment.infra.events;

import com.fabri.srvappointment.domain.event.ScheduledAppointmentEvent;
import com.fabri.srvappointment.domain.gateway.ITriageGateway;
import com.fabri.srvappointment.domain.vo.TriageStatus;
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
public class ScheduledAppointmentListener {

    private final RabbitMqServiceAdapter rabbitMqServiceAdapter;
    private final ITriageGateway triageGateway;

    @EventListener
    public void handleScheduledAppointmentEvent(final ScheduledAppointmentEvent event) {
        log.info("Received scheduled appointment event {}", event);
        log.info("Sending message to Patient and Doctor Email Notification");

        triageGateway.updateStatus(event.getTriageId(), TriageStatus.SCHEDULED_APPOINTMENT);

        EmailNotificationFactory emailNotificationFactory = new EmailNotificationFactory();
        var patientScheduled = emailNotificationFactory.patientScheduled(event);
        rabbitMqServiceAdapter.send(FilaConstants.PATIENT_EMAIL_NOTIFICATION, patientScheduled);

        var doctorScheduled = emailNotificationFactory.doctorAppointmentReminder(event);
        rabbitMqServiceAdapter.send(FilaConstants.DOCTOR_EMAIL_NOTIFICATION, doctorScheduled);
    }
}
