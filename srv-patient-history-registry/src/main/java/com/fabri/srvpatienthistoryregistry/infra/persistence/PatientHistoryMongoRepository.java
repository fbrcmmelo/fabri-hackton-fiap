package com.fabri.srvpatienthistoryregistry.infra.persistence;

import com.fabri.srvpatienthistoryregistry.infra.persistence.entity.PatientHistoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PatientHistoryMongoRepository extends MongoRepository<PatientHistoryEntity, String> {

    List<PatientHistoryEntity> findAllByTriagePatientId(String patientId);
}
