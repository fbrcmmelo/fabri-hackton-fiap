package com.fabri.srvpatienthistoryregistry.infra.adapters;

import com.fabri.srvpatienthistoryregistry.infra.adapters.dto.PatientHistoryDTO;
import com.fabri.srvpatienthistoryregistry.infra.api.PatientOpenAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/patients", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientAPI implements PatientOpenAPI {

    private final PatientHistoryController controller;

    @Override
    @GetMapping("/{patientId}")
    public ResponseEntity<PatientHistoryDTO> getPatientHistory(@PathVariable String patientId) {
        log.info("srv-patient-history-registry: Fetching history for patient ID: {}", patientId);
        return ResponseEntity.ok(controller.findAllPatientHistory(patientId));
    }
}
