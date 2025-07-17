package com.fabri.srv.user.infra.adapters.controller.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DoctorRegisterRequest extends UserRegisterRequest {

    private final String specialization;
    private final String crm;
}
