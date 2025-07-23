package com.fabri.srvappointment.domain.event;

import com.fabri.srvappointment.domain.IDomainEvent;
import com.fabri.srvappointment.domain.Triage;
import com.fabri.srvappointment.domain.vo.TriageStatus;
import lombok.Getter;

import java.time.Instant;

@Getter
public class FinishedPatientTriageEvent implements IDomainEvent {

    private final Long triageId;
    private final Long doctorId;
    private Instant appointmentDate;
    private TriageStatus status;

    public FinishedPatientTriageEvent(Triage triage) {
        this.triageId = triage.getId();
        this.doctorId = triage.getDoctor().getDoctorId();
        this.appointmentDate = triage.getAppointmentDate();
        this.status = triage.getStatus();
    }

    @Override
    public String toString() {
        return "ApprovedTriageEvent";
    }
}
