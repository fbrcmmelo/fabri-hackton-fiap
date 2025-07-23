package com.fabri.srvappointment.infra.adapters.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record StartTriageRequest(
        String doctorId,
        String appointmentSpecialization,
        String priority,
        String patientId,
        Integer patientAge,
        String patientAllergies,
        List<String> patientTaking,
        String patientComplaints,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dateToAppoint,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        LocalTime timeToAppoint) {
}
