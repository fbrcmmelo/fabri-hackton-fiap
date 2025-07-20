package com.fabri.srvappointment.infra.adapters.controller.dto;

public record MedicationRequest(String name, String dosage, Long days, Long takePerDay) {
}
