package com.fabri.srvappointment.domain.event;

import com.fabri.srvappointment.domain.IDomainEvent;
import com.fabri.srvappointment.domain.Triage;
import lombok.Getter;

@Getter
public class StartedPatientTriageEvent implements IDomainEvent {

    private final Triage patientTriage;

    public StartedPatientTriageEvent(Triage triage) {
        this.patientTriage = triage;
    }

    @Override
    public String toString() {
        return "Patient Checked In";
    }
}
