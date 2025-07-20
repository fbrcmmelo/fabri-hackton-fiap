package com.fabri.srvappointment.domain.usecases;

import com.fabri.srvappointment.application.SchedulePatientAppointmentUseCase;
import com.fabri.srvappointment.application.io.ScheduleAppointmentInput;
import com.fabri.srvappointment.application.io.ScheduleAppointmentOutput;
import com.fabri.srvappointment.domain.Appointment;
import com.fabri.srvappointment.domain.IDomainEventPublisher;
import com.fabri.srvappointment.domain.event.ScheduledAppointmentEvent;
import com.fabri.srvappointment.domain.gateway.IAppointmentGateway;
import com.fabri.srvappointment.domain.gateway.ITriageGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchedulePatientAppointmentUseCaseImpl implements SchedulePatientAppointmentUseCase {

    private final IAppointmentGateway appointmentGateway;
    private final ITriageGateway triageGateway;
    private final IDomainEventPublisher domainEventPublisher;

    @Override
    public ScheduleAppointmentOutput execute(ScheduleAppointmentInput input) {
        final var triage = triageGateway.getById(input.getTriageId());
        final var appointment = new Appointment(null, triage);
        domainEventPublisher.publish(new ScheduledAppointmentEvent(appointment));

        return ScheduleAppointmentOutput.from(appointmentGateway.save(appointment));
    }
}
