package com.fabri.srvpatienthistoryregistry.application.io;

import com.fabri.srvpatienthistoryregistry.domain.PatientHistory;
import com.fabri.srvpatienthistoryregistry.domain.vo.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PatientHistoryOutput {

    private String patientId;
    private String patientName;
    private List<HistoryOutput> patientHistory;

    public static PatientHistoryOutput from(Patient patient, List<PatientHistory> history) {
        String patientId = patient.getPatientId();
        String patientName = patient.getPatientName();
        return new PatientHistoryOutput(patientId, patientName, history.stream().map(HistoryOutput::from).toList());
    }
}
