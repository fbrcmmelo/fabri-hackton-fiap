package com.fabri.srv.user.infra.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorAppointmentJpaRepository extends JpaRepository<DoctorAppointmentJpaEntity, Long> {
}
