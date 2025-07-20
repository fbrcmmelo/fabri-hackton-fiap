package com.fabri.srv.user.infra.adapters.controller.dto;

import lombok.Data;

@Data
public class UserRegisterRequest {

    private String username;
    private String password;
    private String email;
    private String address;
    private Integer number;
    private String city;
    private String state;
    private String firstName;
    private String lastName;
    private String cpf;
}
