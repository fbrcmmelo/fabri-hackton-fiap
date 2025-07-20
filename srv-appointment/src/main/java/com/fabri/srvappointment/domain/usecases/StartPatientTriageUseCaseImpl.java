package com.fabri.srvappointment.domain.usecases;

import com.fabri.srvappointment.application.StartPatientTriageUseCase;
import com.fabri.srvappointment.application.io.PatientTriageOutput;
import com.fabri.srvappointment.application.io.StartPatientTriageInput;
import com.fabri.srvappointment.domain.IDomainEventPublisher;
import com.fabri.srvappointment.domain.Triage;
import com.fabri.srvappointment.domain.event.StartedPatientTriageEvent;
import com.fabri.srvappointment.domain.gateway.ITriageGateway;
import com.fabri.srvappointment.domain.gateway.IUserGateway;
import com.fabri.srvappointment.domain.vo.Doctor;
import com.fabri.srvappointment.domain.vo.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartPatientTriageUseCaseImpl implements StartPatientTriageUseCase {

    private final ITriageGateway triageGateway;
    private final IUserGateway userGateway;
    private final IDomainEventPublisher domainEventPublisher;

    @Override
    public PatientTriageOutput execute(StartPatientTriageInput input) {
        final var patient = Patient.from(userGateway.getUser(input.patientId()));
        final var doctor = Doctor.from(userGateway.getDoctor(input.doctorId()));
        final var registeredTriage = triageGateway.save(new Triage(null, input, patient, doctor));

        domainEventPublisher.publish(new StartedPatientTriageEvent(registeredTriage));

        return PatientTriageOutput.from(registeredTriage);
    }
}
