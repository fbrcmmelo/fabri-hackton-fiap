package com.fabri.srvappointment.infra.externals.persistence;

import com.fabri.srvappointment.infra.externals.persistence.entity.TriageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TriageJpaRepository extends JpaRepository<TriageEntity, Long> {
}
