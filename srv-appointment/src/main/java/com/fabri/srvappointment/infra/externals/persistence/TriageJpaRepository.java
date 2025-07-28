package com.fabri.srvappointment.infra.externals.persistence;

import com.fabri.srvappointment.domain.vo.TriageStatus;
import com.fabri.srvappointment.infra.externals.persistence.entity.TriageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TriageJpaRepository extends JpaRepository<TriageEntity, Long> {
    boolean existsByPatientIdAndDoctorIdAndTriageStatusIsIn(Long patientId, Long doctorId, List<TriageStatus> statusLit);
}
