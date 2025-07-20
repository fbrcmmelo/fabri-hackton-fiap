package com.fabri.srvappointment.application.io;

import com.fabri.srvappointment.infra.adapters.controller.dto.ScheduleAppointmentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleAppointmentInput {

    private Long triageId;
    private Long doctorId;

    public static ScheduleAppointmentInput from(ScheduleAppointmentRequest request) {
        return new ScheduleAppointmentInput(
                request.triageId(),
                request.doctorId()
        );
    }
}
