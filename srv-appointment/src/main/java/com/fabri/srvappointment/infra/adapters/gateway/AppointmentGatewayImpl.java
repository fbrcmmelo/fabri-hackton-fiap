package com.fabri.srvappointment.infra.adapters.gateway;

import com.fabri.srvappointment.domain.Appointment;
import com.fabri.srvappointment.domain.gateway.IAppointmentGateway;
import com.fabri.srvappointment.infra.externals.persistence.AppointmentJpaRepository;
import com.fabri.srvappointment.infra.externals.persistence.DoctorPrescriptionRepository;
import com.fabri.srvappointment.infra.externals.persistence.entity.AppointmentEntity;
import com.fabri.srvappointment.infra.externals.persistence.entity.DoctorPrescriptionEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppointmentGatewayImpl implements IAppointmentGateway {

    private final AppointmentJpaRepository repository;
    private final DoctorPrescriptionRepository doctorPrescriptionRepository;

    @Override
    public Appointment save(Appointment appointment) {
        var appointmentToRegister = new AppointmentEntity(appointment);

        if (appointment.getDoctorPrescription() != null) {
            final var registeredDoctorPrescription = doctorPrescriptionRepository.save(
                    new DoctorPrescriptionEntity(appointment.getDoctorPrescription())
            );

            appointmentToRegister.setDoctorPrescription(registeredDoctorPrescription);
        }

        final var registeredAppointment = repository.save(appointmentToRegister);

        return Appointment.from(registeredAppointment);
    }

    @Override
    public Appointment findByTriageId(Long triageId) {
        final var entity = repository.findByTriageId(triageId).orElseThrow(EntityNotFoundException::new);
        return Appointment.from(entity);
    }
}
