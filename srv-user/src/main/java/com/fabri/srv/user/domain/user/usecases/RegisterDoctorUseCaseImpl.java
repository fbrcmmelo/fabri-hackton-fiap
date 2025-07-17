package com.fabri.srv.user.domain.user.usecases;

import com.fabri.srv.user.application.RegisterDoctorUseCase;
import com.fabri.srv.user.application.dto.RegisterDoctorInput;
import com.fabri.srv.user.application.dto.UserOutput;
import com.fabri.srv.user.domain.user.Doctor;
import com.fabri.srv.user.domain.user.service.DoctorDomainService;
import com.fabri.srv.user.domain.user.vo.DoctorCRM;
import com.fabri.srv.user.domain.user.vo.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RegisterDoctorUseCaseImpl implements RegisterDoctorUseCase {

    private final DoctorDomainService doctorDomainService;

    @Override
    public UserOutput execute(RegisterDoctorInput input) {
        Objects.requireNonNull(input, "Input cannot be null");
        var crm = new DoctorCRM(input.getCrm(), input.getSpecialization());
        var doctor = new Doctor(null, input, crm);
        var registeredDoctor = doctorDomainService.register(doctor, RoleEnum.DOCTOR_PENDING);

        return UserOutput.fromDomain(registeredDoctor);
    }
}
