package com.fabri.srv.user.domain.user.vo;

import com.fabri.srv.user.domain.user.Doctor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorAppointment {

    private Long id;
    private Long doctorId;
    private Long triageId;
    private Instant appointmentTime;
    private String status;
    private Doctor doctor;

}
