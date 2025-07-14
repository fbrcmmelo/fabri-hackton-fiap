package com.fabri.srv.user.infra.adapters.controller.dto;

public record DoctorRegisterRequest(
        String username,
        String password,
        String email,
        String firstName,
        String lastName,
        String specialization,
        String licenseNumber,
        String address,
        String city,
        String state,
        Integer number,
        String cpf) {
}
