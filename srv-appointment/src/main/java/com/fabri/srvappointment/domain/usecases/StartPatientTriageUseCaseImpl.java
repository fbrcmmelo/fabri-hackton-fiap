package com.fabri.srvappointment.domain.usecases;

import com.fabri.srvappointment.application.StartPatientTriageUseCase;
import com.fabri.srvappointment.application.io.PatientTriageOutput;
import com.fabri.srvappointment.application.io.StartPatientTriageInput;
import com.fabri.srvappointment.domain.Triage;
import com.fabri.srvappointment.domain.gateway.IUserGateway;
import com.fabri.srvappointment.domain.services.TriageDomainService;
import com.fabri.srvappointment.domain.vo.Doctor;
import com.fabri.srvappointment.domain.vo.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartPatientTriageUseCaseImpl implements StartPatientTriageUseCase {

    private final TriageDomainService domainService;
    private final IUserGateway userGateway;

    @Override
    public PatientTriageOutput execute(StartPatientTriageInput input) {
        final var patient = Patient.from(userGateway.getUser(input.patientId()));
        final var doctor = Doctor.from(userGateway.getUser(input.doctorId()));

        Triage registeredTriage = domainService.saveTriage(new Triage(null, input, patient, doctor));

        return PatientTriageOutput.from(registeredTriage);
    }
}
