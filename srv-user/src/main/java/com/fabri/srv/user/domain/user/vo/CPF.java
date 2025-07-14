package com.fabri.srv.user.domain.user.vo;

import com.fabri.srv.user.infra.exceptions.DomainException;
import lombok.Getter;

import java.util.Objects;

@Getter
public class CPF {
    private String cpfCnpj;

    public CPF(String cpfCnpj) {
        Objects.requireNonNull(cpfCnpj, "Cpf/Cnpj nao pode ser nulo");

        String avaliador = cpfCnpj.replace(".", "")
                .replace("-", "")
                .replace("/", "");

        if (avaliador.length() < 11 || avaliador.length() > 14) {
            throw new DomainException("Cpf/Cnpj inválido");
        }

        this.cpfCnpj = cpfCnpj;
    }

    public String getCpfCnpjSemFormatacao() {
        return this.cpfCnpj.replace(".", "")
                .replace("-", "")
                .replace("/", "");
    }

    public String getCpfFormatado() {
        String retorno = this.getCpfCnpjSemFormatacao();

        if (retorno.length() == 11) {
            return new StringBuilder(retorno.substring(0, 3))
                    .append(".").append(retorno.substring(3, 6))
                    .append(".").append(retorno.substring(6, 9))
                    .append("-").append(retorno.substring(9, 11))
                    .toString();
        }

        return new StringBuilder(retorno.substring(0, 2))
                .append(".").append(retorno.substring(2, 5))
                .append(".").append(retorno.substring(5, 8))
                .append("/").append(retorno.substring(8, 12))
                .append("-").append(retorno.substring(12, 14))
                .toString();
    }
}