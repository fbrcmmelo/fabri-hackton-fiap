package com.fabri.srvappointment.domain.event;

import com.fabri.srvappointment.domain.IDomainEvent;
import com.fabri.srvappointment.domain.Triage;
import lombok.Getter;

@Getter
public class FinishedPatientTriageEvent implements IDomainEvent {

    private final Long triageId;
    private final Long doctorId;

    public FinishedPatientTriageEvent(Triage triage) {
        this.triageId = triage.getId();
        this.doctorId = triage.getDoctor().getDoctorId();
    }

    @Override
    public String toString() {
        return "ApprovedTriageEvent";
    }
}
