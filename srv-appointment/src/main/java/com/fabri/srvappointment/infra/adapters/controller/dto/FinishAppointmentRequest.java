package com.fabri.srvappointment.infra.adapters.controller.dto;

import java.util.List;

public record FinishAppointmentRequest(
        Long triageId,
        Long doctorId,
        String notes,
        List<MedicationRequest> medications,
        List<ExamRequest> exams
) {
}
