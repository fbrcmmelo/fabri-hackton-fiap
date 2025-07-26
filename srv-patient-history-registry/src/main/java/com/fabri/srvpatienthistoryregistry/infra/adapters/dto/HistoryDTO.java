package com.fabri.srvpatienthistoryregistry.infra.adapters.dto;

import com.fabri.srvpatienthistoryregistry.application.io.HistoryOutput;
import com.fabri.srvpatienthistoryregistry.application.io.PatientHistoryInput;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistoryDTO {

    private PatientHistoryInput historyInput;

    public static HistoryDTO from(HistoryOutput historyOutput) {
        return new HistoryDTO(historyOutput.getPatientAppointment());
    }
}
