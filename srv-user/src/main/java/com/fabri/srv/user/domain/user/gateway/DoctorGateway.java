package com.fabri.srv.user.domain.user.gateway;

import com.fabri.srv.user.domain.user.Doctor;

import java.util.Optional;

public interface DoctorGateway {
    Doctor save(Doctor user);
    Optional<Doctor> findByCRM(String crm);
}
