package com.fabri.srvappointment.domain.services;

import com.fabri.srvappointment.domain.Appointment;
import com.fabri.srvappointment.domain.IDomainEventPublisher;
import com.fabri.srvappointment.domain.event.FinishedAppointmentEvent;
import com.fabri.srvappointment.domain.event.ScheduledAppointmentEvent;
import com.fabri.srvappointment.domain.gateway.IAppointmentGateway;
import com.fabri.srvappointment.domain.gateway.IUserGateway;
import com.fabri.srvappointment.infra.adapters.controller.dto.SaveNextAppointment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class AppointmentDomainService {

    private final IAppointmentGateway appointmentGateway;
    private final IDomainEventPublisher domainEventPublisher;
    private final IUserGateway userGateway;

    public Appointment scheduleAppointment(Appointment appointment) {
        final var registeredAppointment = appointmentGateway.save(appointment);

        userGateway.saveNextAvailableAppointment(new SaveNextAppointment(
                registeredAppointment.getTriage().getDoctor().getDoctorId(),
                registeredAppointment.getTriage().getId(),
                registeredAppointment.getTriage().getAppointmentDate())
        );
        domainEventPublisher.publish(new ScheduledAppointmentEvent(registeredAppointment));
        return registeredAppointment;
    }

    public Appointment finishAppointment(Appointment appointmentOnExecution) {
        final var finishedAppointment = appointmentGateway.save(appointmentOnExecution);
        domainEventPublisher.publish(new FinishedAppointmentEvent(finishedAppointment));
        return finishedAppointment;
    }

    public Appointment findByTriageId(Long triageId) {
        return appointmentGateway.findByTriageId(triageId);
    }
}
