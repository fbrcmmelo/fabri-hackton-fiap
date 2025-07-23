package com.fabri.srv.user.application.dto;

import com.fabri.srv.user.domain.user.vo.DoctorAppointment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class AppointmentOutput {

    Long id;
    Long appointmentId;
    Instant appointmentTime;
    String status;

    public static AppointmentOutput fromDomain(DoctorAppointment doctorAppointment) {
        return new AppointmentOutput(
                doctorAppointment.getId(),
                doctorAppointment.getTriageId(),
                doctorAppointment.getAppointmentTime(),
                doctorAppointment.getStatus()
        );
    }
}
