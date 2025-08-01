package com.fabri.srvpatienthistoryregistry.infra.persistence.entity;


import com.fabri.srvpatienthistoryregistry.domain.vo.DoctorPrescription;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class DoctorPrescriptionEntity {

    private String notes;
    private String patientId;
    private String doctorCrm;
    private List<MedicationEntity> medications;
    private List<ExamEntity> exams;

    public DoctorPrescriptionEntity(DoctorPrescription doctorPrescription) {
        this.patientId = doctorPrescription.toPatientId();
        this.doctorCrm = doctorPrescription.doctorCrm();
        this.notes = doctorPrescription.notes();

        this.medications = doctorPrescription.medications().stream()
                .map(MedicationEntity::new)
                .collect(Collectors.toList());

        this.exams = doctorPrescription.exams().stream()
                .map(ExamEntity::new)
                .collect(Collectors.toList());
    }
}
