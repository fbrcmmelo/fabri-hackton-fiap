package com.fabri.srvpatienthistoryregistry.infra.persistence.entity;

import com.fabri.srvpatienthistoryregistry.domain.vo.Medication;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MedicationEntity {

    private String name;
    private String dosage;
    private Long daysToTake;
    private Long takePerDay;

    public MedicationEntity(Medication medication) {
        if (medication == null) {
            return;
        }

        this.name = medication.getName();
        this.dosage = medication.getDosage();
        this.daysToTake = medication.getDays();
        this.takePerDay = medication.getTimesPerDay();
    }
}
