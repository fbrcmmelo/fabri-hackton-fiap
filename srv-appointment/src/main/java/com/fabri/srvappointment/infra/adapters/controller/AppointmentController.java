package com.fabri.srvappointment.infra.adapters.controller;

import com.fabri.srvappointment.application.FinishAppointmentUseCase;
import com.fabri.srvappointment.application.SchedulePatientAppointmentUseCase;
import com.fabri.srvappointment.application.io.FinishAppointmentInput;
import com.fabri.srvappointment.application.io.ScheduleAppointmentInput;
import com.fabri.srvappointment.infra.adapters.controller.dto.FinishAppointmentRequest;
import com.fabri.srvappointment.infra.adapters.controller.dto.FinishedAppointmentDTO;
import com.fabri.srvappointment.infra.adapters.controller.dto.ScheduleAppointmentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppointmentController {

    private final SchedulePatientAppointmentUseCase schedulePatientAppointmentUseCase;
    private final FinishAppointmentUseCase finishAppointmentUseCase;

    public void scheduleAppointment(ScheduleAppointmentRequest request) {
        try {
            var in = ScheduleAppointmentInput.from(request);
            schedulePatientAppointmentUseCase.execute(in);
        } catch (Exception e) {
            log.error("srv-appointment: Fail to schedule appointment : ", e.getMessage());
        }
    }

    public FinishedAppointmentDTO finishAppointment(FinishAppointmentRequest request) {
        var in = FinishAppointmentInput.from(request);
        var out = finishAppointmentUseCase.execute(in);
        return FinishedAppointmentDTO.from(out);
    }
}
