package com.fabri.srvappointment.infra.externals.api;

import com.fabri.srvappointment.infra.adapters.controller.dto.FinishAppointmentRequest;
import com.fabri.srvappointment.infra.adapters.controller.dto.FinishedAppointmentDTO;
import org.springframework.http.ResponseEntity;

public interface AppointmentOpenAPI {

    ResponseEntity<FinishedAppointmentDTO> finishAppointment(FinishAppointmentRequest request);
}
