package com.fabri.srvpatienthistoryregistry.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@NoArgsConstructor
public class Patient {

    private String patientId;
    private String patientName;
    private String patientEmail;

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
