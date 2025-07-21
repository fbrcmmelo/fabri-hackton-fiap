package com.fabri.srvpatienthistoryregistry.domain.usecases;

import com.fabri.srvpatienthistoryregistry.application.io.PatientHistoryInput;
import com.fabri.srvpatienthistoryregistry.application.usecases.RegisterPatientHistoryUseCase;
import com.fabri.srvpatienthistoryregistry.domain.PatientHistory;
import com.fabri.srvpatienthistoryregistry.domain.services.PatientDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RegisterPatientHistoryUseCaseImpl implements RegisterPatientHistoryUseCase {

    private final PatientDomainService patientDomainService;

    @Override
    public void execute(PatientHistoryInput input) {
        Objects.requireNonNull(input, "Input cannot be null");
        patientDomainService.registerHistory(new PatientHistory(null, input));
    }
}
