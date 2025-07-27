package com.fabri.srvappointment.infra.externals.api;

import com.fabri.srvappointment.infra.adapters.controller.AppointmentController;
import com.fabri.srvappointment.infra.adapters.controller.dto.FinishAppointmentRequest;
import com.fabri.srvappointment.infra.adapters.controller.dto.FinishedAppointmentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(
        value = "/api/v1/appointments",
        consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
)
public class AppointmentAPI implements AppointmentOpenAPI {

    private final AppointmentController controller;

    @Override
    @PostMapping
    public ResponseEntity<FinishedAppointmentDTO> finishAppointment(@RequestBody FinishAppointmentRequest request) {
        return ResponseEntity.ok(controller.finishAppointment(request));
    }
}
