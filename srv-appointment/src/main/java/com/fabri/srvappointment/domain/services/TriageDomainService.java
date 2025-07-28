package com.fabri.srvappointment.domain.services;

import com.fabri.srvappointment.application.io.FinishTriageInput;
import com.fabri.srvappointment.domain.IDomainEventPublisher;
import com.fabri.srvappointment.domain.Triage;
import com.fabri.srvappointment.domain.gateway.ITriageGateway;
import com.fabri.srvappointment.domain.vo.TriageStatus;
import com.fabri.srvappointment.infra.client.user.UserOutput;
import com.fabri.srvappointment.infra.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class TriageDomainService {

    private final ITriageGateway triageGateway;
    private final IDomainEventPublisher domainEventPublisher;

    public Triage saveTriage(Triage triage) {
        final var triageAlreadyStarted = triageGateway.getByPatientIdAndDoctorIdAndStatusIn(
                triage.getPatient().getPatientId(),
                triage.getDoctor().getDoctorId(),
                List.of(
                        TriageStatus.PENDING_DOCTOR_APPROVAL,
                        TriageStatus.SCHEDULING_APPOINTMENT
                )
        );

        if (triageAlreadyStarted) {
            throw new DomainException("Triage already started with this doctor");
        }

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
