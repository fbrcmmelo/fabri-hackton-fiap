package com.fabri.srvappointment.application.io;

import com.fabri.srvappointment.domain.vo.TriageStatus;
import com.fabri.srvappointment.infra.adapters.controller.dto.FinishTriageRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FinishTriageInput {

    private Long triageId;
    private Long doctorId;
    private TriageStatus triageStatus;

    public static FinishTriageInput from(FinishTriageRequest request) {
        return new FinishTriageInput(
                request.triageId(),
                request.doctorId(),
                request.status()
        );
    }
}
