package com.fabri.srvpatienthistoryregistry.infra.adapters.dto;

import com.fabri.srvpatienthistoryregistry.domain.PatientHistory;
import lombok.Getter;

@Getter
public class RegisterAppointmentRequest {

    private final PatientHistory patientHistory;

    public RegisterAppointmentRequest(PatientHistory patientHistoryFinished) {
        this.patientHistory = patientHistoryFinished;
    }
}
