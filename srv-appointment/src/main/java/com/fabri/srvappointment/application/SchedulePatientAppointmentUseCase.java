package com.fabri.srvappointment.application;

import com.fabri.srvappointment.application.io.ScheduleAppointmentInput;
import com.fabri.srvappointment.application.io.ScheduleAppointmentOutput;
import com.fabri.srvappointment.infra.IOUseCase;

public interface SchedulePatientAppointmentUseCase extends
        IOUseCase<ScheduleAppointmentInput, ScheduleAppointmentOutput> {

}
