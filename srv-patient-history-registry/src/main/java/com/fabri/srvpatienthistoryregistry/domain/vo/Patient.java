package com.fabri.srvpatienthistoryregistry.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
public class Patient {

    private final String patientId;
    private final String patientName;
    private final String patientEmail;

    @Setter
    private Integer patientAge;

    public Patient(String patientId, String patientName, String patientEmail) {
        Objects.requireNonNull(patientId);
        Objects.requireNonNull(patientName);

        this.patientId = patientId;
        this.patientName = patientName;
        this.patientEmail = patientEmail;
    }

}
