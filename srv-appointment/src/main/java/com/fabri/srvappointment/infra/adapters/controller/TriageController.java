package com.fabri.srvappointment.infra.adapters.controller;

import com.fabri.srvappointment.application.FinishPatientTriageUseCase;
import com.fabri.srvappointment.application.StartPatientTriageUseCase;
import com.fabri.srvappointment.application.io.FinishTriageInput;
import com.fabri.srvappointment.application.io.StartPatientTriageInput;
import com.fabri.srvappointment.infra.adapters.controller.dto.FinishTriageRequest;
import com.fabri.srvappointment.infra.adapters.controller.dto.StartTriageRequest;
import com.fabri.srvappointment.infra.adapters.controller.dto.TriageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TriageController {

    private final StartPatientTriageUseCase startPatientTriageUseCase;
    private final FinishPatientTriageUseCase finishPatientTriageUseCase;

    public TriageDTO startTriage(StartTriageRequest request) {
        var output = startPatientTriageUseCase.execute(StartPatientTriageInput.from(request));
        return TriageDTO.from(output);
    }

    public TriageDTO finishTriage(FinishTriageRequest request) {
        var output = finishPatientTriageUseCase.execute(FinishTriageInput.from(request));
        return TriageDTO.from(output);
    }
}
