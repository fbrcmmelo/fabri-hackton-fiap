package com.fabri.srvpatienthistoryregistry.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    private String doctorId;
    private String doctorName;
    private String doctorCrm;
    private String doctorEmail;

    public Doctor(String doctorId, String doctorName, String doctorEmail) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.doctorEmail = doctorEmail;
    }

    public static Doctor from(DoctorOutput doctor) {
        Objects.requireNonNull(doctor, "Failed to map DoctorOutput is null");

        return new Doctor(
                doctor.getDoctorId(),
                doctor.getDoctorName(),
                doctor.getDoctorCrm(),
                doctor.getDoctorEmail()
        );
    }
}
