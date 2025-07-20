package com.fabri.srvappointment.application.io;

import com.fabri.srvappointment.infra.adapters.controller.dto.StartTriageRequest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record StartPatientTriageInput(
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
    public static StartPatientTriageInput from(StartTriageRequest request) {
        return new StartPatientTriageInput(
                request.doctorId(),
                request.appointmentSpecialization(),
                request.priority(),
                request.patientId(),
                request.patientAge(),
                request.patientAllergies(),
                request.patientTaking(),
                request.patientComplaints(),
                request.dateToAppoint(),
                request.timeToAppoint()
        );
    }
}
