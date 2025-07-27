package com.fabri.srvpatienthistoryregistry.infra.adapters.dto;

import com.fabri.srvpatienthistoryregistry.application.io.PatientHistoryInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PatientHistoryRequest {

    private String id;
    private PatientHistoryInput patientAppointment;
}
