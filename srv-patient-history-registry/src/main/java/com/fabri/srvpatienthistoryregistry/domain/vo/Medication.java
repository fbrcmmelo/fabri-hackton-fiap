package com.fabri.srvpatienthistoryregistry.domain.vo;

import com.fabri.srvpatienthistoryregistry.infra.persistence.entity.MedicationEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Medication {

    private String name;
    private String dosage;
    private Long days;
    private Long timesPerDay;

    private Medication(String name) {
        this.name = name;
    }

    public static Medication fromEntity(MedicationEntity medicationEntity) {
        if (medicationEntity == null) {
            return null;
        }
        return new Medication(
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
