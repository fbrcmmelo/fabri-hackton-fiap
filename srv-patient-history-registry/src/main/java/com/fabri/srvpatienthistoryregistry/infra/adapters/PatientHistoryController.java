package com.fabri.srvpatienthistoryregistry.infra.adapters;

import com.fabri.srvpatienthistoryregistry.application.io.PatientHistoryInput;
import com.fabri.srvpatienthistoryregistry.application.io.PatientHistoryOutput;
import com.fabri.srvpatienthistoryregistry.application.usecases.FindPatientHistoryUseCase;
import com.fabri.srvpatienthistoryregistry.application.usecases.RegisterPatientHistoryUseCase;
import com.fabri.srvpatienthistoryregistry.infra.adapters.dto.PatientHistoryDTO;
import com.fabri.srvpatienthistoryregistry.infra.adapters.dto.RegisterAppointmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PatientHistoryController {

    private final RegisterPatientHistoryUseCase registerPatientHistoryUseCase;
    private final FindPatientHistoryUseCase findPatientHistoryUseCase;

    public void registerPatientHistory(RegisterAppointmentRequest request) {
        final var input = PatientHistoryInput.from(request);
        registerPatientHistoryUseCase.execute(input);
    }

    public PatientHistoryDTO findAllPatientHistory(String patientId) {
        PatientHistoryOutput output = findPatientHistoryUseCase.execute(patientId);
        return PatientHistoryDTO.from(output);
    }
}
