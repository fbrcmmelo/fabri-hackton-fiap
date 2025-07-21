package com.fabri.srvpatienthistoryregistry.infra.consumers;

import com.fabri.srvmessagebroker.infra.consts.FilaConstants;
import com.fabri.srvpatienthistoryregistry.infra.Consumer;
import com.fabri.srvpatienthistoryregistry.infra.adapters.PatientHistoryController;
import com.fabri.srvpatienthistoryregistry.infra.adapters.dto.RegisterAppointmentRequest;
import com.fabri.srvpatienthistoryregistry.infra.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PatientHistoryConsumer implements Consumer {

    private final JsonUtils jsonUtils;
    private final PatientHistoryController patientHistoryController;

    @Override
    @RabbitListener(queues = FilaConstants.FINISHED_PATIENT_APPOINTMENT)
    public void consume(String message) {
        log.info("Received patient appointment finished message: {}", message);
        try {
            final var request = jsonUtils.fromJson(message, RegisterAppointmentRequest.class);
            patientHistoryController.registerPatientHistory(request);
        } catch (Exception e) {
            log.error("Error processing patient appointment finished message: {}", message, e);
        }
    }
}
