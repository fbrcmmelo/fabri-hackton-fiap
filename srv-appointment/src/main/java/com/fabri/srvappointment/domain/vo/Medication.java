package com.fabri.srvappointment.domain.vo;

import com.fabri.srvappointment.infra.adapters.controller.dto.MedicationRequest;
import com.fabri.srvappointment.infra.exception.DomainException;
import com.fabri.srvappointment.infra.externals.persistence.entity.MedicationEntity;
import lombok.Getter;

import java.util.Optional;

@Getter
public class Medication {

    private Long id;
    private String name;
    private String dosage;
    private Long days;
    private Long timesPerDay;

    private Medication(String name) {
        this.name = name;
    }

    public Medication(Long id, String name, String dosage, Long days, Long timesPerDay) {
        this.id = id;
        this.name = Optional.ofNullable(name)
                .orElseThrow(() -> new DomainException("Medication name cannot be null"));
        this.dosage = Optional.ofNullable(dosage)
                .orElseThrow(() -> new DomainException("Medication dosage cannot be null"));
        this.days = Optional.ofNullable(days)
                .orElseThrow(() -> new DomainException("Medication days cannot be null"));
        this.timesPerDay = Optional.ofNullable(timesPerDay)
                .orElseThrow(() -> new DomainException("Medication times per day cannot be null"));
    }

    public static Medication from(MedicationRequest medicationRequest) {
        return new Medication(
                null, // Id set to null for new medications
                medicationRequest.name(),
                medicationRequest.dosage(),
                medicationRequest.days(),
                medicationRequest.takePerDay()
        );
    }

    public static Medication fromEntity(MedicationEntity medicationEntity) {
        if (medicationEntity == null) {
            return null;
        }
        return new Medication(
                medicationEntity.getId(),
                medicationEntity.getName(),
                medicationEntity.getDosage(),
                medicationEntity.getDaysToTake(),
                medicationEntity.getTakePerDay()
        );
    }

    public static Medication fromTriage(String name) {
        return new Medication(name);
    }
}
