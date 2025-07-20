package com.fabri.srv.user.application.dto;

import com.fabri.srv.user.infra.adapters.controller.dto.ActivateDoctorRequest;

public record ActivateDoctorInput(String activatorUsername,
                                  String activatorPassword,
                                  String crm,
                                  String doctorCpf) {

    public static ActivateDoctorInput fromRequest(ActivateDoctorRequest request) {
        return new ActivateDoctorInput(
                request.activatorUsername(),
                request.activatorPassword(),
                request.crm(),
                request.doctorCpf()
        );
    }
}
