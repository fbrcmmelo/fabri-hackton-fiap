package com.fabri.srvpatienthistoryregistry.application.io;

import com.fabri.srvpatienthistoryregistry.domain.PatientHistory;
import com.fabri.srvpatienthistoryregistry.domain.vo.AppointmentStatus;
import com.fabri.srvpatienthistoryregistry.domain.vo.DoctorPrescription;
import com.fabri.srvpatienthistoryregistry.domain.vo.PatientTriage;
import com.fabri.srvpatienthistoryregistry.infra.adapters.dto.RegisterAppointmentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class PatientHistoryInput {

    private String appointmentId;
    private PatientTriage patientTriage;
    private Instant createdAt;
    private Instant appointmentAt;
    private Instant finishedAt;
    private AppointmentStatus status;
    private DoctorPrescription doctorPrescription;

    public static PatientHistoryInput from(RegisterAppointmentRequest request) {
        PatientHistory patientHistory = request.getPatientHistory();
        Objects.requireNonNull(patientHistory, "Patient history cannot be null");

        PatientHistoryInput patientAppointment = patientHistory.getPatientAppointment();
        Objects.requireNonNull(patientAppointment, "Patient appointment cannot be null");

        return new PatientHistoryInput(
                patientHistory.getId(),
                patientAppointment.getPatientTriage(),
                patientAppointment.getCreatedAt(),
                patientAppointment.getAppointmentAt(),
                patientAppointment.getFinishedAt(),
                patientAppointment.getStatus(),
                patientAppointment.getDoctorPrescription()
        );
    }
}
