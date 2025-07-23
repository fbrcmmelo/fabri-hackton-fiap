package com.fabri.srvappointment.infra.adapters.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class SaveNextAppointment {

    private Long doctorId;
    private Long triageId;
    private Instant appointmentTime;
}
