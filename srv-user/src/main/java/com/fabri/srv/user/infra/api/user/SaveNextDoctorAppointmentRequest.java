package com.fabri.srv.user.infra.api.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class SaveNextDoctorAppointmentRequest {

    private Long triageId;
    private Instant nextAvailableAppointment;

}
