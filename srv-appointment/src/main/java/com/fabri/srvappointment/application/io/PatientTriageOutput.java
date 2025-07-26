package com.fabri.srvappointment.application.io;

import com.fabri.srvappointment.domain.Triage;
import com.fabri.srvappointment.domain.vo.Doctor;
import com.fabri.srvappointment.domain.vo.Medication;
import com.fabri.srvappointment.domain.vo.Patient;
import com.fabri.srvappointment.domain.vo.TriageStatus;

import java.time.Instant;
import java.util.List;


public record PatientTriageOutput(
        Long id,
        Instant triageTime,
        Instant appointmentTime,
        Patient patient,
        Doctor doctor,
        List<Medication> patientTakenMedications,
        String patientAllergies,
        String patientComplaints,
        TriageStatus status) {

    public static PatientTriageOutput from(Triage triage) {
        return new PatientTriageOutput(
                triage.getId(),
                triage.getTriageDate(),
                triage.getAppointmentDate(),
                triage.getPatient(),
                triage.getDoctor(),
                triage.getPatientTakenMedications(),
                triage.getPatientAllergies(),
                triage.getPatientComplaint(),
                triage.getStatus()
        );
    }

    public static PatientTriageOutput fromFinishTriage(Triage triage) {
        return new PatientTriageOutput(
                triage.getId(),
                triage.getTriageDate(),
                triage.getAppointmentDate(),
                triage.getPatient(),
                triage.getDoctor(),
                triage.getPatientTakenMedications(),
                triage.getPatientAllergies(),
                triage.getPatientComplaint(),
                TriageStatus.SCHEDULING_APPOINTMENT
        );
    }
}
