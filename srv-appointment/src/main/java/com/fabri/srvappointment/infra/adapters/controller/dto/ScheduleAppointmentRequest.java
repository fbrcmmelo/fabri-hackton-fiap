package com.fabri.srvappointment.infra.adapters.controller.dto;

public record ScheduleAppointmentRequest(
        Long triageId,
        Long doctorId
        ) {
}
