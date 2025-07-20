package com.fabri.srvappointment.infra.externals.persistence;

import com.fabri.srvappointment.infra.externals.persistence.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppointmentJpaRepository extends JpaRepository<AppointmentEntity, Long> {

    Optional<AppointmentEntity> findByTriageId(Long triageId);
}
