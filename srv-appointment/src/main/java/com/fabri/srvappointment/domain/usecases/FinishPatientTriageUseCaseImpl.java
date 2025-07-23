package com.fabri.srvappointment.domain.usecases;

import com.fabri.srvappointment.application.FinishPatientTriageUseCase;
import com.fabri.srvappointment.application.io.FinishTriageInput;
import com.fabri.srvappointment.application.io.PatientTriageOutput;
import com.fabri.srvappointment.domain.IDomainEventPublisher;
import com.fabri.srvappointment.domain.gateway.ITriageGateway;
import com.fabri.srvappointment.domain.gateway.IUserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FinishPatientTriageUseCaseImpl implements FinishPatientTriageUseCase {

    private final ITriageGateway triageGateway;
    private final IUserGateway userGateway;
    private final IDomainEventPublisher domainEventPublisher;

    @Override
    public PatientTriageOutput execute(FinishTriageInput input) {
        final var triagePeddingApproval = triageGateway.getById(input.getTriageId());
        final var doctorOutput = userGateway.getUser(String.valueOf(input.getDoctorId()));

        triagePeddingApproval.updateStatus(doctorOutput.getId(), input.getTriageStatus());
        final var triageUpdated = triageGateway.save(triagePeddingApproval);
        domainEventPublisher.publish(triageUpdated.getEvent());

        return PatientTriageOutput.from(triageUpdated);
    }
}
