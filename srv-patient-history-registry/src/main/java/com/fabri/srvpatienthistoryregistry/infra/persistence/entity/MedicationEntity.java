package com.fabri.srvpatienthistoryregistry.infra.persistence.entity;

import com.fabri.srvpatienthistoryregistry.domain.vo.Medication;
import lombok.Getter;

@Getter
public class MedicationEntity {

    private final String name;
    private final String dosage;
    private final Long daysToTake;
    private final Long takePerDay;

    public MedicationEntity(Medication medication) {
        if (medication == null) {
            throw new IllegalArgumentException("Medication cannot be null");
        }

        this.name = medication.getName();
        this.dosage = medication.getDosage();
        this.daysToTake = medication.getDays();
        this.takePerDay = medication.getTimesPerDay();
    }
}
