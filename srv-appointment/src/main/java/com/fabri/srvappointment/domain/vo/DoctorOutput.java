package com.fabri.srvappointment.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class DoctorOutput {

    private Long doctorId;
    private String doctorName;
    private String description;
    private String doctorEmail;
    private String doctorCrm;
    private Instant nextAvailableAppointment;
    private Long appointmentDurationInMinutes;
}
