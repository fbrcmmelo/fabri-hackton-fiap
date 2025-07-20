package com.fabri.srv.user.domain.user.events;

import com.fabri.srv.user.domain.user.Doctor;
import lombok.Getter;

import java.util.Objects;

@Getter
public class ActivatedDoctorEvent extends RegisteredDoctorEvent {

    private final String adminId;
    private final String userName;

    public ActivatedDoctorEvent(Doctor doctor, Long adminId) {
        super(doctor);
        Objects.requireNonNull(adminId, "Admin ID cannot be null, to activate a doctor");
        this.adminId = String.valueOf(adminId);
        this.userName = doctor.getUsername();
    }

    @Override
    public String toString() {
        return "Doctor activated";
    }

}
