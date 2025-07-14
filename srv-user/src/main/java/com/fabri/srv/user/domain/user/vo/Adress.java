package com.fabri.srv.user.domain.user.vo;

import com.fabri.srv.user.infra.exceptions.DomainException;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Adress {

    private Integer number;
    private String address;
    private String city;
    private String state;

    public Adress(Integer number, String address, String city, String estado) {
        Objects.requireNonNull(number, "Numero não pode estar null");
        Objects.requireNonNull(address, "Logradouro não pode estar null");
        Objects.requireNonNull(city, "Cidade não pode estar null");
        Objects.requireNonNull(estado, "Estado não pode estar null");

        validate(address, city, estado);

        this.number = number;
        this.address = address;
        this.city = city;
        this.state = estado;
    }

    private static void validate(String lougradouro, String cidade, String estado) {
        if (lougradouro.isEmpty()) {
            throw new DomainException("Logradouro não pode estar em branco");
        }

        if (cidade.isEmpty()) {
            throw new DomainException("Cidade não pode estar em branco");
        }

        if (estado.isEmpty()) {
            throw new DomainException("Estado não pode estar em branco");
        }
    }

}
