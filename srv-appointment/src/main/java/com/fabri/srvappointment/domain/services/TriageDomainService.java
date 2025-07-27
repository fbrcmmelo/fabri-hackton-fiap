package com.fabri.srvappointment.domain.services;

import com.fabri.srvappointment.application.io.FinishTriageInput;
import com.fabri.srvappointment.domain.IDomainEventPublisher;
import com.fabri.srvappointment.domain.Triage;
import com.fabri.srvappointment.domain.gateway.ITriageGateway;
import com.fabri.srvappointment.infra.client.user.UserOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class TriageDomainService {

    private final ITriageGateway triageGateway;
    private final IDomainEventPublisher domainEventPublisher;

    public Triage saveTriage(Triage triage) {
        Triage registeredTriage = triageGateway.save(triage);
        domainEventPublisher.publish(registeredTriage.getEvent());

        return registeredTriage;
    }

    public Triage finishTriage(FinishTriageInput input, UserOutput doctorOutput) {
        Triage triagePeddingApproval = triageGateway.getById(input.getTriageId());
        triagePeddingApproval.updateStatus(doctorOutput.getId(), input.getTriageStatus());

        final var triageUpdated = triageGateway.save(triagePeddingApproval);
        domainEventPublisher.publish(triageUpdated.getEvent());

        return triageUpdated;
    }
}
