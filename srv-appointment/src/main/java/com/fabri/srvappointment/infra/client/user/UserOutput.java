package com.fabri.srvappointment.infra.client.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOutput {
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private String doctorCrm;
    private Instant nextAvailableAppointment;
    private Long appointmentDurationInMinutes;

}
