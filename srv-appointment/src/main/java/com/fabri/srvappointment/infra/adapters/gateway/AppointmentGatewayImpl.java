package com.fabri.srvappointment.infra.adapters.gateway;

import com.fabri.srvappointment.domain.Appointment;
import com.fabri.srvappointment.domain.gateway.IAppointmentGateway;
import com.fabri.srvappointment.infra.externals.persistence.AppointmentJpaRepository;
import com.fabri.srvappointment.infra.externals.persistence.entity.AppointmentEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppointmentGatewayImpl implements IAppointmentGateway {

    private final AppointmentJpaRepository repository;

    @Override
    public Appointment save(Appointment appointment) {
        final var entity = repository.save(new AppointmentEntity(appointment));
        return Appointment.from(entity);
    }

    @Override
    public Appointment findByTriageId(Long triageId) {
        final var entity = repository.findByTriageId(triageId).orElseThrow(EntityNotFoundException::new);
        return Appointment.from(entity);
    }
}
