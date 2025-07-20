package com.fabri.srvappointment.application.io;

import com.fabri.srvappointment.domain.vo.Medication;

public record RequestedMedicationOutput(String name, String dosage, Long days, Long timesPerDay) {

    public static RequestedMedicationOutput from(Medication medication) {
        return new RequestedMedicationOutput(
                medication.getName(),
                medication.getDosage(),
                medication.getDays(),
                medication.getTimesPerDay()
        );
    }
}
