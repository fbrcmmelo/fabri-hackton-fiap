package com.fabri.srv.user.infra.adapters.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class DoctorDTO {

    private Long id;
    private String name;
    private String scpecialization;
    private String email;
    private String crm;
    private Instant nextAppointmentTime;

}
