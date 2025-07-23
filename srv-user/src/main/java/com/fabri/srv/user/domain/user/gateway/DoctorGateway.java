package com.fabri.srv.user.domain.user.gateway;

import com.fabri.srv.user.domain.user.Doctor;
import com.fabri.srv.user.domain.user.vo.DoctorAppointment;

import java.util.Optional;

public interface DoctorGateway {
    Doctor save(Doctor user);
    Optional<Doctor> findByCRM(String crm);

    Doctor findById(Long doctorId);

    void saveNextAppointment(DoctorAppointment confirmado);
}
