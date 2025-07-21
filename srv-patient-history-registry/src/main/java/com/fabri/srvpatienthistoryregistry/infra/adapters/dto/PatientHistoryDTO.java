package com.fabri.srvpatienthistoryregistry.infra.adapters.dto;

import com.fabri.srvpatienthistoryregistry.application.io.PatientHistoryOutput;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PatientHistoryDTO {

    private String patientId;
    private String patientName;
    private List<HistoryDTO> patientHistory;

    public static PatientHistoryDTO from(PatientHistoryOutput output) {
        return new PatientHistoryDTO(
                output.getPatientId(),
                output.getPatientName(),
                output.getPatientHistory().stream()
                        .map(HistoryDTO::from)
                        .toList()
        );
    }
}
