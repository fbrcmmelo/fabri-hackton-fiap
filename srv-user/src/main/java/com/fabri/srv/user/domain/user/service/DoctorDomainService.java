package com.fabri.srv.user.domain.user.service;

import com.fabri.srv.user.domain.IDomainEventPubGateway;
import com.fabri.srv.user.domain.user.Doctor;
import com.fabri.srv.user.domain.user.events.ActivatedDoctorEvent;
import com.fabri.srv.user.domain.user.events.RegisteredDoctorEvent;
import com.fabri.srv.user.domain.user.gateway.DoctorGateway;
import com.fabri.srv.user.domain.user.gateway.RoleGateway;
import com.fabri.srv.user.domain.user.vo.RoleEnum;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class DoctorDomainService {

    private final DoctorGateway doctorGateway;
    private final RoleGateway roleGateway;
    private final UserValidatorHandler userValidatorHandler;
    private final IDomainEventPubGateway domainEventPubGateway;

    public Doctor register(Doctor doctor, RoleEnum role) {
        userValidatorHandler.validateUserToRegister(doctor);
        Doctor registeredDoctor = doctorGateway.save(doctor.withRoles(Set.of(roleGateway.byEnum(role))));
        domainEventPubGateway.publish(new RegisteredDoctorEvent(registeredDoctor));

        return registeredDoctor;
    }

    public Doctor activate(Doctor doctor, Long adminId) {
        Doctor activatedDoctor = doctorGateway.save(doctor.withRoles(Set.of(roleGateway.byEnum(RoleEnum.DOCTOR))));
        domainEventPubGateway.publish(new ActivatedDoctorEvent(doctor, adminId));

        return activatedDoctor;
    }

    public Doctor findByCRM(String crm) {
        return doctorGateway.findByCRM(crm).orElseThrow(EntityNotFoundException::new);
    }
}
