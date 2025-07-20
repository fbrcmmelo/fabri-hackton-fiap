package com.fabri.srv.user.domain.user.vo;

import java.util.Optional;

public record DoctorCRM(String crm, String specialization) {

    public DoctorCRM {
        var crmRequired = Optional.ofNullable(crm).orElse("");
        if (crmRequired.isBlank()) {
            throw new IllegalArgumentException("crm is required");
        }

        var specializationRequired = Optional.ofNullable(specialization).orElse("");
        if (specializationRequired.isBlank()) {
            throw new IllegalArgumentException("Specialization cannot be null or blank.");
        }
    }
}
