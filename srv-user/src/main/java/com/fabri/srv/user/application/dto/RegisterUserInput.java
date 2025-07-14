package com.fabri.srv.user.application.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class RegisterUserInput {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;
    private String city;
    private String state;
    private String address;
    private Integer number;

}
