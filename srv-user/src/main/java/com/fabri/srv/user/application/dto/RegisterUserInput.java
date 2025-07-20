package com.fabri.srv.user.application.dto;

import com.fabri.srv.user.infra.adapters.controller.dto.UserRegisterRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
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

    public static RegisterUserInput from(UserRegisterRequest request) {
        return RegisterUserInput.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .cpf(request.getCpf())
                .city(request.getCity())
                .state(request.getState())
                .address(request.getAddress())
                .number(request.getNumber())
                .build();
    }
}
