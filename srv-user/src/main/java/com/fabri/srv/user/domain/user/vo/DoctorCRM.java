package com.fabri.srv.user.domain.user.vo;

import com.fabri.srv.user.infra.exceptions.DomainException;

import java.util.Optional;

public record DoctorCRM(String crm, String specialization) {

    public DoctorCRM {
        var crmRequired = Optional.ofNullable(crm).orElse("");
        if (crmRequired.isBlank()) {
            throw new DomainException("crm is required");
        }

        var specializationRequired = Optional.ofNullable(specialization).orElse("");
        if (specializationRequired.isBlank()) {
            throw new DomainException("Specialization cannot be null or blank.");
        }
    }
}
