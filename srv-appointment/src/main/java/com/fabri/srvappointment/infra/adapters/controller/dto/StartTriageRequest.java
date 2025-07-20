package com.fabri.srvappointment.infra.adapters.controller.dto;

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
        LocalDate dateToAppoint,
        LocalTime timeToAppoint) {
}
