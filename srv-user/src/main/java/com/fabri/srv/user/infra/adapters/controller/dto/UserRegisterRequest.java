package com.fabri.srv.user.infra.adapters.controller.dto;

public record UserRegisterRequest(
        String username,
        String password,
        String email,
        String address,
        Integer number,
        String city,
        String state,
        String firstName,
        String lastName,
        String cpf) {
}
