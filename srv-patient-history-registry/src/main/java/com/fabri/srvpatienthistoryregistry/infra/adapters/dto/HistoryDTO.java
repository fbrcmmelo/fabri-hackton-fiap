package com.fabri.srvpatienthistoryregistry.infra.adapters.dto;

import com.fabri.srvpatienthistoryregistry.application.io.HistoryOutput;
import com.fabri.srvpatienthistoryregistry.application.io.PatientHistoryInput;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HistoryDTO {

    private PatientHistoryInput historyInput;

    public static HistoryDTO from(HistoryOutput historyOutput) {
        return new HistoryDTO(
                historyOutput.getPatientAppointment()
        );
    }
}
