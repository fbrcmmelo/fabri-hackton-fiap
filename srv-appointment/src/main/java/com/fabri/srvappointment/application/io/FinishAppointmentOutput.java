package com.fabri.srvappointment.application.io;

import com.fabri.srvappointment.domain.Appointment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FinishAppointmentOutput {

    private Long appointmentId;
    private Long triageId;
    private String doctorCrm;
    private Long patientId;
    private String notes;
    private List<RequestedMedicationOutput> medications;
    private List<RequestedExamOupt> exams;

    public static FinishAppointmentOutput from(Appointment appointmentFinished) {
        return new FinishAppointmentOutput(
                appointmentFinished.getAppointmentId(),
                appointmentFinished.getTriage().getId(),
                appointmentFinished.getDoctorPrescription().doctorCrm(),
                appointmentFinished.getDoctorPrescription().toPatientId(),
                appointmentFinished.getDoctorPrescription().notes(),
                appointmentFinished.getDoctorPrescription().medications().stream()
                        .map(RequestedMedicationOutput::from).toList(),
                appointmentFinished.getDoctorPrescription().exams().stream().map(RequestedExamOupt::from).toList()
        );
    }
}
