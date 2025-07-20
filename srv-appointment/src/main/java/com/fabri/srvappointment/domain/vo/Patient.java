package com.fabri.srvappointment.domain.vo;

import com.fabri.srvappointment.infra.client.user.UserOutput;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
public class Patient {

    private final Long patientId;
    private final String patientName;
    private final String patientEmail;

    @Setter
    private Integer patientAge;

    public Patient(Long patientId, String patientName, String patientEmail) {
        Objects.requireNonNull(patientId);
        Objects.requireNonNull(patientName);

        this.patientId = patientId;
        this.patientName = patientName;
        this.patientEmail = patientEmail;
    }

    public static Patient from(UserOutput user) {
        Objects.requireNonNull(user, "Failed to map UserOutput is null");
        return new Patient(
                Long.valueOf(user.userId()),
                user.name(),
                user.email()
        );
    }
}
