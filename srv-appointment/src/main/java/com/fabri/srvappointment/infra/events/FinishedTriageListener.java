package com.fabri.srvappointment.infra.events;

import com.fabri.srvappointment.domain.event.FinishedPatientTriageEvent;
import com.fabri.srvappointment.domain.gateway.IUserGateway;
import com.fabri.srvappointment.infra.adapters.controller.dto.SaveNextAppointment;
import com.fabri.srvmessagebroker.domain.RabbitMqServiceAdapter;
import com.fabri.srvmessagebroker.infra.consts.FilaConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FinishedTriageListener {

    private final RabbitMqServiceAdapter rabbitMqServiceAdapter;
    private final IUserGateway userGateway;

    @EventListener
    public void handleScheduledAppointmentEvent(final FinishedPatientTriageEvent event) {
        log.info("Received approved triage event {}", event);
        log.info("Sending to approved triage queue");
        rabbitMqServiceAdapter.send(FilaConstants.FINISHED_PATIENT_TRIAGE, event);

        userGateway.saveNextAvailableAppointment(new SaveNextAppointment(
                event.getDoctorId(),
                event.getTriageId(),
                event.getAppointmentDate())
        );
    }
}
