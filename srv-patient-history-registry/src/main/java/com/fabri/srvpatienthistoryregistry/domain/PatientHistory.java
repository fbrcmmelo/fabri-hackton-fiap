package com.fabri.srvpatienthistoryregistry.domain;

import com.fabri.srvpatienthistoryregistry.application.io.PatientHistoryInput;
import com.fabri.srvpatienthistoryregistry.domain.vo.DoctorPrescription;
import com.fabri.srvpatienthistoryregistry.domain.vo.PatientTriage;
import com.fabri.srvpatienthistoryregistry.infra.persistence.entity.PatientHistoryEntity;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Objects;

@Getter
public class PatientHistory extends AbstractAggregateRoot<PatientHistory> {

    private final String id;
    private final PatientHistoryInput patientAppointment;

    public PatientHistory(String id, PatientHistoryInput input) {
        Objects.requireNonNull(input, "Triage cannot be null to create an Appointment");
        this.id = id;
        this.patientAppointment = input;
    }

    public static PatientHistory from(PatientHistoryEntity entity) {
        if (entity == null) {
            return null;
        }
        var entityInput = new PatientHistoryInput(
                entity.getId(),
                PatientTriage.from(entity.getTriage()),
                entity.getCreatedAt(),
                entity.getTriage().getAppointmentSuposedDate(),
                entity.getFinishedAt(),
                entity.getStatus(),
                DoctorPrescription.from(entity.getDoctorPrescription())
        );

        return new PatientHistory(entity.getId(), entityInput);
    }

}
