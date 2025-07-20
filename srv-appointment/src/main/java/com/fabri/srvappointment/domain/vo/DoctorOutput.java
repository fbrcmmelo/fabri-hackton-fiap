package com.fabri.srvappointment.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DoctorOutput {

    private Long doctorId;
    private String doctorName;
    private String description;
    private String doctorEmail;
    private String doctorCrm;
    private List<AvailableDoctorAgenda> availableAppointments;
}
