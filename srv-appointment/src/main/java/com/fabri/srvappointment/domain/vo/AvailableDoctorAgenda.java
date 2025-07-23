package com.fabri.srvappointment.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class AvailableDoctorAgenda {

    private Instant appointmentTime;
}
