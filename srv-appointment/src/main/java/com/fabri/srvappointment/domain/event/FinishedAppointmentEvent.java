package com.fabri.srvappointment.domain.event;

import com.fabri.srvappointment.domain.Appointment;
import com.fabri.srvappointment.domain.IDomainEvent;
import lombok.Getter;

@Getter
public class FinishedAppointmentEvent implements IDomainEvent {

    private final Appointment appointment;

    public FinishedAppointmentEvent(Appointment appointmentFinished) {
        this.appointment = appointmentFinished;
    }

    @Override
    public String toString() {
        return "Finished Appointment";
    }
}
