package com.fabri.srvappointment.application.io;

import com.fabri.srvappointment.domain.Appointment;
import com.fabri.srvappointment.domain.vo.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class ScheduleAppointmentOutput {

    private Long appointmentId;
    private Long triageId;
    private AppointmentStatus status;
    private Instant appointmentDate;

    public static ScheduleAppointmentOutput from(Appointment appointment) {
        return new ScheduleAppointmentOutput(
                appointment.getAppointmentId(),
                appointment.getTriage().getId(),
                appointment.getStatus(),
                appointment.getAppointmentAt()
        );
    }
}
