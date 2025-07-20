package com.fabri.srvappointment.domain.gateway;

import com.fabri.srvappointment.domain.Appointment;

public interface IAppointmentGateway {

    Appointment save(Appointment appointment);

    Appointment findByTriageId(Long triageId);
}
