package com.fabri.srv.user.domain.user.usecases;

import com.fabri.srv.user.application.dto.SaveNextAppointmentInput;
import com.fabri.srv.user.domain.user.Doctor;
import com.fabri.srv.user.domain.user.gateway.DoctorGateway;
import com.fabri.srv.user.domain.user.vo.DoctorAppointment;
import com.fabri.srv.user.infra.adapters.controller.SaveNextDoctorAppointmentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveNextDoctorAppointmentUseCaseImpl implements SaveNextDoctorAppointmentUseCase {

    private final DoctorGateway doctorGateway;

    @Override
    public Void execute(SaveNextAppointmentInput input) {
        Doctor byId = doctorGateway.findById(input.getDoctorId());
        DoctorAppointment confirmado = new DoctorAppointment(null, byId.getId(), input.getTriageId(), input.getAppointmentDate(), "CONFIRMADO");

        doctorGateway.saveNextAppointment(confirmado);
        return null;
    }
}
