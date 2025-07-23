package com.fabri.srvappointment.domain.usecases;

import com.fabri.srvappointment.application.FinishAppointmentUseCase;
import com.fabri.srvappointment.application.io.FinishAppointmentInput;
import com.fabri.srvappointment.application.io.FinishAppointmentOutput;
import com.fabri.srvappointment.domain.IDomainEventPublisher;
import com.fabri.srvappointment.domain.event.FinishedAppointmentEvent;
import com.fabri.srvappointment.domain.gateway.IAppointmentGateway;
import com.fabri.srvappointment.domain.gateway.ITriageGateway;
import com.fabri.srvappointment.domain.gateway.IUserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FinishAppointmentUseCaseImpl implements FinishAppointmentUseCase {

    private final ITriageGateway triageGateway;
    private final IAppointmentGateway appointmentGateway;
    private final IUserGateway userGateway;
    private final IDomainEventPublisher domainEventPublisher;

    @Override
    public FinishAppointmentOutput execute(FinishAppointmentInput input) {
        final var triage = triageGateway.getById(input.getTriageId());
        final var doctor = userGateway.getUser(String.valueOf(triage.getDoctor().getDoctorId()));
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor not found for the given check-in.");
        }

        final var appointmentOnExecution = appointmentGateway.findByTriageId(triage.getId());
        appointmentOnExecution.createDoctorPrescription(input.getNotes(), input.getMedications(), input.getExams());
        appointmentOnExecution.finishAppointment();

        var appointmentFinished = appointmentGateway.save(appointmentOnExecution);
        domainEventPublisher.publish(new FinishedAppointmentEvent(appointmentFinished));

        return FinishAppointmentOutput.from(appointmentFinished);
    }
}
