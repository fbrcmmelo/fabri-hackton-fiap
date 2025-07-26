package com.fabri.srvpatienthistoryregistry.application.io;

import com.fabri.srvpatienthistoryregistry.domain.vo.AppointmentStatus;
import com.fabri.srvpatienthistoryregistry.domain.vo.DoctorPrescription;
import com.fabri.srvpatienthistoryregistry.domain.vo.PatientTriage;
import com.fabri.srvpatienthistoryregistry.infra.adapters.dto.RegisterAppointmentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class PatientHistoryInput {

    private String appointmentId;
    private PatientTriage patientTriage;
    private Instant appointmentAt;
    private Instant appointmentFinishedAt;
    private AppointmentStatus status;
    private DoctorPrescription doctorPrescription;

    public static PatientHistoryInput from(RegisterAppointmentRequest request) {
        return new PatientHistoryInput(
                String.valueOf(request.getAppointmentId()),
                request.getTriage(),
                request.getAppointmentAt(),
                request.getFinishedAt(),
                request.getStatus(),
                request.getDoctorPrescription()
        );
    }
}
