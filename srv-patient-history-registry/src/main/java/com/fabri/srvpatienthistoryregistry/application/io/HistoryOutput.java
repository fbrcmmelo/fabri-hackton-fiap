package com.fabri.srvpatienthistoryregistry.application.io;

import com.fabri.srvpatienthistoryregistry.domain.PatientHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HistoryOutput {

    private PatientHistoryInput patientAppointment;

    public static HistoryOutput from(PatientHistory patientHistory) {
        return new HistoryOutput(patientHistory.getPatientAppointment());
    }
}
