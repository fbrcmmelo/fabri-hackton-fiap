package com.fabri.srv.user.infra.adapters.gateway;

import com.fabri.srv.user.domain.user.Doctor;
import com.fabri.srv.user.domain.user.gateway.DoctorGateway;
import com.fabri.srv.user.domain.user.vo.DoctorAppointment;
import com.fabri.srv.user.infra.persistence.user.DoctorAppointmentJpaEntity;
import com.fabri.srv.user.infra.persistence.user.DoctorAppointmentJpaRepository;
import com.fabri.srv.user.infra.persistence.user.DoctorJpaEntity;
import com.fabri.srv.user.infra.persistence.user.UserJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DoctorGatewayImpl implements DoctorGateway {

    private final UserJpaRepository jpaRepository;
    private final DoctorAppointmentJpaRepository doctorAppointmentJpaRepository;

    @Override
    public Doctor save(Doctor user) {
        return Doctor.fromJpaEntity(jpaRepository.save(new DoctorJpaEntity(user)));
    }

    @Override
    public Optional<Doctor> findByCRM(String crm) {
        return jpaRepository.findByCrm(crm).map(Doctor::fromJpaEntity);
    }

    @Override
    public Doctor findById(Long doctorId) {
        return jpaRepository.doctorById(doctorId)
                .map(Doctor::fromJpaEntity)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + doctorId));
    }

    @Override
    @Transactional
    public void saveNextAppointment(DoctorAppointment confirmado) {
        DoctorAppointmentJpaEntity from = DoctorAppointmentJpaEntity.from(confirmado);
        doctorAppointmentJpaRepository.save(from);

        DoctorJpaEntity doctor = from.getDoctor();
        doctor.setNextAppointment(from.getAppointmentTime());
        jpaRepository.save(doctor);
    }

}
