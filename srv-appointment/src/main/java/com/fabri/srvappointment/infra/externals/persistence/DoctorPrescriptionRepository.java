package com.fabri.srvappointment.infra.externals.persistence;

import com.fabri.srvappointment.infra.externals.persistence.entity.DoctorPrescriptionEntity;
import org.springframework.data.repository.CrudRepository;

public interface DoctorPrescriptionRepository extends CrudRepository<DoctorPrescriptionEntity, Long> {
}
