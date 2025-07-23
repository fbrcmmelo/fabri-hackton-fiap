package com.fabri.srv.user.infra.adapters.controller.dto;

import com.fabri.srv.user.application.dto.AppointmentOutput;

import java.time.Instant;

public record AppointmentDTO(
        Long id,
        Long appointmentId,
        Instant appointmentTime,
        String status) {
    public static AppointmentDTO from(AppointmentOutput appointmentOutput) {
        return new AppointmentDTO(
                appointmentOutput.getId(),
                appointmentOutput.getAppointmentId(),
                appointmentOutput.getAppointmentTime(),
                appointmentOutput.getStatus()
        );
    }
}
