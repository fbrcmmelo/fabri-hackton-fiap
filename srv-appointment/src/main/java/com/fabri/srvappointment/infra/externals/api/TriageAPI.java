package com.fabri.srvappointment.infra.externals.api;

import com.fabri.srvappointment.infra.adapters.controller.TriageController;
import com.fabri.srvappointment.infra.adapters.controller.dto.FinishTriageRequest;
import com.fabri.srvappointment.infra.adapters.controller.dto.StartTriageRequest;
import com.fabri.srvappointment.infra.adapters.controller.dto.TriageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(
        value = "/api/v1/triages",
        consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
)
public class TriageAPI implements TriageOpenAPI {

    private final TriageController controller;

    @Override
    @PostMapping
    public ResponseEntity<TriageDTO> startTriage(@RequestBody StartTriageRequest request) {
        log.info("srv-appointment: Received request to start patient triage of id: ", request.patientId());
        return ResponseEntity.ok(controller.startTriage(request));
    }

    @Override
    @PutMapping
    public ResponseEntity<TriageDTO> finishTriage(@RequestBody FinishTriageRequest request) {
        log.info("srv-appointment: Received request to finish patient triage of id: {}", request.triageId());
        return ResponseEntity.ok(controller.finishTriage(request));
    }
}
