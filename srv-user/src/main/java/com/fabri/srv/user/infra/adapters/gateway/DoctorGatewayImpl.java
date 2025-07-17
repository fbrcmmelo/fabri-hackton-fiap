package com.fabri.srv.user.infra.adapters.gateway;

import com.fabri.srv.user.domain.user.Doctor;
import com.fabri.srv.user.domain.user.gateway.DoctorGateway;
import com.fabri.srv.user.infra.persistence.user.DoctorJpaEntity;
import com.fabri.srv.user.infra.persistence.user.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DoctorGatewayImpl implements DoctorGateway {

    private final UserJpaRepository jpaRepository;

    @Override
    public Doctor save(Doctor user) {
        return Doctor.fromJpaEntity(jpaRepository.save(new DoctorJpaEntity(user)));
    }

    @Override
    public Optional<Doctor> findByCRM(String crm) {
        return jpaRepository.findByCrm(crm).map(Doctor::fromJpaEntity);
    }

}
