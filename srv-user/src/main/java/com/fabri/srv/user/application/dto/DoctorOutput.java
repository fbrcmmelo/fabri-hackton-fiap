package com.fabri.srv.user.application.dto;

import com.fabri.srv.user.domain.user.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class DoctorOutput {

    private Long id;
    private String name;
    private String email;
    private String crm;
    private String specialization;
    private Instant nextAppointmentTime;

    public static DoctorOutput from(Doctor byId) {
        if (byId == null) {
            return null;
        }
        return new DoctorOutput(
                byId.getId(),
                byId.getFullName().getName(),
                byId.getEmail().getValue(),
                byId.getCrm().crm(),
                byId.getCrm().specialization(),
                byId.getNextAppointmentTime()
        );
    }
}
