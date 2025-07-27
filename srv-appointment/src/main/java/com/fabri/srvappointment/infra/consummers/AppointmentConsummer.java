package com.fabri.srvappointment.infra.consummers;

import com.fabri.srvappointment.infra.adapters.controller.AppointmentController;
import com.fabri.srvappointment.infra.adapters.controller.dto.ScheduleAppointmentRequest;
import com.fabri.srvappointment.infra.config.JsonUtils;
import com.fabri.srvmessagebroker.infra.consts.FilaConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppointmentConsummer {

    private final AppointmentController appointmentController;
    private final JsonUtils jsonUtils;

    @RabbitListener(queues = FilaConstants.FINISHED_PATIENT_TRIAGE)
    public void handleScheduledAppointmentEvent(final String message) {
        log.info("Received message for scheduling appointment");
        appointmentController.scheduleAppointment(jsonUtils.fromJson(message, ScheduleAppointmentRequest.class));
    }
}
