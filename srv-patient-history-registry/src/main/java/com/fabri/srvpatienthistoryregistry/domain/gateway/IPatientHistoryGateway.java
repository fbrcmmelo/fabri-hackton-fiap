package com.fabri.srvpatienthistoryregistry.domain.gateway;

import com.fabri.srvpatienthistoryregistry.domain.PatientHistory;

import java.util.List;

public interface IPatientHistoryGateway {
    void register(PatientHistory patientHistory);
    List<PatientHistory> findAllByPatientId(String patientId);
}
