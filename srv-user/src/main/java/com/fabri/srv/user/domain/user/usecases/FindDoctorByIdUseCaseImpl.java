package com.fabri.srv.user.domain.user.usecases;

import com.fabri.srv.user.application.FindDoctorByIdUseCase;
import com.fabri.srv.user.application.dto.DoctorOutput;
import com.fabri.srv.user.domain.user.gateway.DoctorGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindDoctorByIdUseCaseImpl implements FindDoctorByIdUseCase {

    private final DoctorGateway doctorGateway;

    @Override
    public DoctorOutput execute(String input) {
        return DoctorOutput.from(doctorGateway.findById(Long.valueOf(input)));
    }
}
