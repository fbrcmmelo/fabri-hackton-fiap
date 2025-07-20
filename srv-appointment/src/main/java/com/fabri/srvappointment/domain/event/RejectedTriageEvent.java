package com.fabri.srvappointment.domain.event;

import com.fabri.srvappointment.domain.IDomainEvent;
import com.fabri.srvappointment.domain.Triage;
import com.fabri.srvappointment.domain.vo.Doctor;
import com.fabri.srvappointment.domain.vo.Patient;
import lombok.Getter;

@Getter
public class RejectedTriageEvent implements IDomainEvent {

    private final Long triageId;
    private final String reason;
    private final Doctor doctor;
    private final Patient patient;

    public RejectedTriageEvent(Triage triage) {
        this.triageId = triage.getId();
        this.reason = triage.getRejectionReason();
        this.doctor = triage.getDoctor();
        this.patient = triage.getPatient();
    }

    @Override
    public String toString() {
        return "Rejected Appointment Event";
    }
}
