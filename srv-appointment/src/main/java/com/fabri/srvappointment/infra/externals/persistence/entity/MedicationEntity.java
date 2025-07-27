package com.fabri.srvappointment.infra.externals.persistence.entity;

import com.fabri.srvappointment.domain.vo.Medication;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_prescription_medication")
public class MedicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String dosage;
    private Long daysToTake;
    private Long takePerDay;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "medications")
    private Set<DoctorPrescriptionEntity> doctorPrescription = new HashSet<>();

    public MedicationEntity(Medication medication) {
        if (medication == null) {
            throw new IllegalArgumentException("Medication cannot be null");
        }
        this.id = medication.getId();
        this.name = medication.getName();
        this.dosage = medication.getDosage();
        this.daysToTake = medication.getDays();
        this.takePerDay = medication.getTimesPerDay();
    }
}
