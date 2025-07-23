package com.fabri.srv.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class SaveNextAppointmentInput {

    private Long doctorId;
    private Long triageId;
    private Instant appointmentDate;

}
