package com.fabri.srvpatienthistoryregistry.infra.adapters;

import com.fabri.srvpatienthistoryregistry.domain.PatientHistory;
import com.fabri.srvpatienthistoryregistry.domain.gateway.IPatientHistoryGateway;
import com.fabri.srvpatienthistoryregistry.infra.persistence.PatientHistoryMongoRepository;
import com.fabri.srvpatienthistoryregistry.infra.persistence.entity.PatientHistoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PatientHistoryGatewayImpl implements IPatientHistoryGateway {

    private final PatientHistoryMongoRepository repository;

    @Override
    public void register(PatientHistory patientHistory) {
        repository.save(new PatientHistoryEntity(patientHistory));
    }

    @Override
    public List<PatientHistory> findAllByPatientId(String patientId) {
        return repository.findAllByTriagePatientId(patientId)
                .stream()
                .map(PatientHistory::from)
                .toList();
    }
}
