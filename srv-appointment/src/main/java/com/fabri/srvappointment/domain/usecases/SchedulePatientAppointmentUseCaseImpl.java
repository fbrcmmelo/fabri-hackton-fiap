package com.fabri.srvappointment.domain.usecases;

import com.fabri.srvappointment.application.SchedulePatientAppointmentUseCase;
import com.fabri.srvappointment.application.io.ScheduleAppointmentInput;
import com.fabri.srvappointment.application.io.ScheduleAppointmentOutput;
import com.fabri.srvappointment.domain.Appointment;
import com.fabri.srvappointment.domain.gateway.ITriageGateway;
import com.fabri.srvappointment.domain.services.AppointmentDomainService;
import com.fabri.srvappointment.domain.vo.TriageStatus;
import com.fabri.srvappointment.infra.exception.DomainException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SchedulePatientAppointmentUseCaseImpl implements SchedulePatientAppointmentUseCase {

    private final AppointmentDomainService domainService;
    private final ITriageGateway triageGateway;

    @Override
    public ScheduleAppointmentOutput execute(ScheduleAppointmentInput input) {
        final var triage = triageGateway.getById(input.getTriageId());

        if (triage.getStatus().isScheduledAppointment()) {
            throw new DomainException("Triage has already been scheduled");
        }

        try {
            final var registeredAppointment = domainService.scheduleAppointment(new Appointment(null, triage));
            return ScheduleAppointmentOutput.from(registeredAppointment);
        } catch (Exception e) {
            log.error("Fail to schedule appointment of triage id {}, ",triage.getId(), e);
            triage.setRejectionReason(e.getMessage());
            triageGateway.updateStatus(triage.getId(), TriageStatus.ERROR);
            throw new DomainException(e.getMessage());
        }
    }
}
