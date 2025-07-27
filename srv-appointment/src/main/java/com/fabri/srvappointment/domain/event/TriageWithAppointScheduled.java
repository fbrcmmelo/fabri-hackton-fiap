package com.fabri.srvappointment.domain.event;

import com.fabri.srvappointment.domain.IDomainEvent;
import com.fabri.srvappointment.domain.Triage;
import lombok.Getter;

@Getter
public class TriageWithAppointScheduled implements IDomainEvent {

    private Triage triage;

    public TriageWithAppointScheduled(Triage triage) {
        this.triage = triage;
    }

    @Override
    public String toString() {
        return "Triage with appointment scheduled: " + triage.getId();
    }
}
