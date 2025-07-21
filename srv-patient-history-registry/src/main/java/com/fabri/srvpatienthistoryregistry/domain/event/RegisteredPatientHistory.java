package com.fabri.srvpatienthistoryregistry.domain.event;

import lombok.Getter;

@Getter
public class RegisteredPatientHistory {

    private final String patientId;

    public RegisteredPatientHistory(String patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "Registered Patient History Event for patient ID: " + patientId;
    }
}
