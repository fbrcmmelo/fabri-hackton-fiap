package com.fabri.srvappointment.infra.externals.api;

import com.fabri.srvappointment.infra.adapters.controller.dto.FinishTriageRequest;
import com.fabri.srvappointment.infra.adapters.controller.dto.StartTriageRequest;
import com.fabri.srvappointment.infra.adapters.controller.dto.TriageDTO;
import org.springframework.http.ResponseEntity;

public interface TriageOpenAPI {

    ResponseEntity<TriageDTO> startTriage(StartTriageRequest request);
    ResponseEntity<TriageDTO> finishTriage(FinishTriageRequest request);
}
