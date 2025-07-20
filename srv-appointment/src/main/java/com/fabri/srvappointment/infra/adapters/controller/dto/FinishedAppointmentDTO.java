package com.fabri.srvappointment.infra.adapters.controller.dto;

import com.fabri.srvappointment.application.io.FinishAppointmentOutput;
import com.fabri.srvappointment.application.io.RequestedExamOupt;
import com.fabri.srvappointment.application.io.RequestedMedicationOutput;

import java.util.List;

public record FinishedAppointmentDTO(
        Long appointmentId,
        String doctorCrm,
        Long patientId,
        String notes,
        List<RequestedMedicationOutput> medications,
        List<RequestedExamOupt> exams
) {
    public static FinishedAppointmentDTO from(FinishAppointmentOutput output) {
        return new FinishedAppointmentDTO(
                output.getAppointmentId(),
                output.getDoctorCrm(),
                output.getPatientId(),
                output.getNotes(),
                output.getMedications(),
                output.getExams()
        );
    }
}
