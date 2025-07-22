package com.fabri.srv.user.domain.user.usecases;

import com.fabri.srv.user.application.ActivateDoctorUseCase;
import com.fabri.srv.user.application.dto.ActivateDoctorInput;
import com.fabri.srv.user.application.dto.UserOutput;
import com.fabri.srv.user.domain.user.Doctor;
import com.fabri.srv.user.domain.user.User;
import com.fabri.srv.user.domain.user.gateway.UserGateway;
import com.fabri.srv.user.domain.user.service.DoctorDomainService;
import com.fabri.srv.user.infra.exceptions.DoctorActivationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ActivateDoctorUseCaseImpl implements ActivateDoctorUseCase {

    private final DoctorDomainService doctorDomainService;
    private final UserGateway userGateway;

    @Override
    public UserOutput execute(ActivateDoctorInput input) {
        User activator = userGateway
                .findByUsername(input.activatorUsername())
                .orElseThrow(DoctorActivationException::new);
        activator.checkRoleToActiveDoctor();
        activator.checkPassword(input.activatorPassword());

        Doctor doctor = doctorDomainService.findByCRM(input.crm());
        doctor.checkCPF(input.doctorCpf());

        return UserOutput.fromDomain(doctorDomainService.activate(doctor, activator.getId()));
    }
}
