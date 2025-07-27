package com.fabri.srvappointment.domain.usecases;

import com.fabri.srvappointment.application.SchedulePatientAppointmentUseCase;
import com.fabri.srvappointment.application.io.ScheduleAppointmentInput;
import com.fabri.srvappointment.application.io.ScheduleAppointmentOutput;
import com.fabri.srvappointment.domain.Appointment;
import com.fabri.srvappointment.domain.gateway.ITriageGateway;
import com.fabri.srvappointment.domain.services.AppointmentDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchedulePatientAppointmentUseCaseImpl implements SchedulePatientAppointmentUseCase {

    private final AppointmentDomainService domainService;
    private final ITriageGateway triageGateway;

    @Override
    public ScheduleAppointmentOutput execute(ScheduleAppointmentInput input) {
        final var triage = triageGateway.getById(input.getTriageId());
        final var registeredAppointment = domainService.scheduleAppointment(new Appointment(null, triage));
        return ScheduleAppointmentOutput.from(registeredAppointment);
    }
}
