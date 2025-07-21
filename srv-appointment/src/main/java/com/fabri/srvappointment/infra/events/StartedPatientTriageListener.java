package com.fabri.srvappointment.infra.events;

import com.fabri.srvappointment.domain.event.StartedPatientTriageEvent;
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
public class StartedPatientTriageListener {

    private final RabbitMqServiceAdapter rabbitMqServiceAdapter;

    @EventListener
    public void patientTriage(final StartedPatientTriageEvent event) {
        log.info("Patient Checked In Event: {}", event);

        log.info("Sending patient check in info to EMR System");
        rabbitMqServiceAdapter.send(FilaConstants.STARTED_PATIENT_TRIAGE, event.getPatientTriage());

        EmailNotificationFactory emailNotificationFactory = new EmailNotificationFactory();

        log.info("Sending message to Patient about check in");
        var patientCheckedIn = emailNotificationFactory.patientCheckedIn(event);
        rabbitMqServiceAdapter.send(FilaConstants.PATIENT_EMAIL_NOTIFICATION, patientCheckedIn);

        log.info("Sending message to Doctor pending approval");
        var doctorApprovalPending = emailNotificationFactory.doctorApprovalPending(event);
        rabbitMqServiceAdapter.send(FilaConstants.DOCTOR_EMAIL_NOTIFICATION, doctorApprovalPending);
    }
}
