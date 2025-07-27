package com.fabri.srvappointment.domain.usecases;

import com.fabri.srvappointment.application.FinishPatientTriageUseCase;
import com.fabri.srvappointment.application.io.FinishTriageInput;
import com.fabri.srvappointment.application.io.PatientTriageOutput;
import com.fabri.srvappointment.domain.Triage;
import com.fabri.srvappointment.domain.gateway.IUserGateway;
import com.fabri.srvappointment.domain.services.TriageDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FinishPatientTriageUseCaseImpl implements FinishPatientTriageUseCase {

    private final IUserGateway userGateway;
    private final TriageDomainService triageDomainService;

    @Override
    public PatientTriageOutput execute(FinishTriageInput input) {
        final var doctorOutput = userGateway.getUser(String.valueOf(input.getDoctorId()));
        Triage finishedTriage = triageDomainService.finishTriage(input, doctorOutput);

        return PatientTriageOutput.fromFinishTriage(finishedTriage);
    }
}
