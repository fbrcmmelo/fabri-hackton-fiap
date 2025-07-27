package com.fabri.srvpatienthistoryregistry.infra.adapters.dto;

import com.fabri.srvpatienthistoryregistry.domain.vo.AppointmentStatus;
import com.fabri.srvpatienthistoryregistry.domain.vo.DoctorPrescription;
import com.fabri.srvpatienthistoryregistry.domain.vo.PatientTriage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterAppointmentRequest {

    private Long appointmentId;
    private PatientTriage triage;
    private Instant createdAt;
    private Instant appointmentAt;
    private Instant finishedAt;
    private AppointmentStatus status;
    private DoctorPrescription doctorPrescription;

}
