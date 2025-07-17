package com.fabri.srv.user.infra.adapters.controller.dto;

public record ActivateDoctorRequest(String activatorUsername,
                                    String activatorPassword,
                                    String crm,
                                    String doctorCpf) {
}
