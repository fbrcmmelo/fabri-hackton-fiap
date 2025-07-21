package com.fabri.srvpatienthistoryregistry.domain.services;

import com.fabri.srvpatienthistoryregistry.domain.PatientHistory;
import com.fabri.srvpatienthistoryregistry.domain.gateway.IPatientHistoryGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientDomainService {

    private final IPatientHistoryGateway patientHistoryGateway;

    public void registerHistory(PatientHistory patientHistory) {
        patientHistoryGateway.register(patientHistory);
    }

    public List<PatientHistory> findHistoryByPatientId(String patientId) {
        return patientHistoryGateway.findAllByPatientId(patientId);
    }
}
