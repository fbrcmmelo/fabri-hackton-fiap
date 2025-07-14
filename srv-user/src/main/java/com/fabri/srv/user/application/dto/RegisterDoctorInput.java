package com.fabri.srv.user.application.dto;

public record RegisterDoctorInput(String username,
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
                                  String cpf
) {
}
