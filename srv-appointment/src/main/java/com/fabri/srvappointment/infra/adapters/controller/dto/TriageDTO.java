package com.fabri.srvappointment.infra.adapters.controller.dto;

import com.fabri.srvappointment.application.io.PatientTriageOutput;
import com.fabri.srvappointment.domain.vo.TriageStatus;

public record TriageDTO(
        Long triageId,
        TriageStatus status
) {
    public static TriageDTO from(PatientTriageOutput output) {
        return new TriageDTO(output.id(), output.status());
    }
}
