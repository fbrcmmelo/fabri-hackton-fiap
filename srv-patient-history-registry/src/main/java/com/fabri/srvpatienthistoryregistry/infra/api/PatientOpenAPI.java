package com.fabri.srvpatienthistoryregistry.infra.api;

import com.fabri.srvpatienthistoryregistry.infra.adapters.dto.PatientHistoryDTO;
import org.springframework.http.ResponseEntity;

public interface PatientOpenAPI {

    ResponseEntity<PatientHistoryDTO> getPatientHistory(String patientId);
}
