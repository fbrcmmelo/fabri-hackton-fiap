package com.fabri.srvappointment.infra.adapters.controller.dto;

import com.fabri.srvappointment.domain.vo.TriageStatus;

public record FinishTriageRequest(
        Long doctorId,
        Long triageId,
        TriageStatus status) {
}
