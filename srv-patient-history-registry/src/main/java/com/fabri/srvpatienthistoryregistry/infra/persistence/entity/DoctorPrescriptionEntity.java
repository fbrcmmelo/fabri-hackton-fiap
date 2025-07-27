package com.fabri.srvpatienthistoryregistry.infra.persistence.entity;


import com.fabri.srvpatienthistoryregistry.domain.vo.DoctorPrescription;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class DoctorPrescriptionEntity {

    private String id;
    private String notes;
    private String patientId;
    private String doctorCrm;
    private List<MedicationEntity> medications;
    private List<ExamEntity> exams;

    public DoctorPrescriptionEntity(DoctorPrescription doctorPrescription) {
        this.patientId = doctorPrescription.toPatientId();
        this.doctorCrm = doctorPrescription.doctorCrm();
        this.notes = doctorPrescription.notes();
        this.medications =
                doctorPrescription.medications().stream().map(MedicationEntity::new).collect(Collectors.toList());
        this.exams = doctorPrescription.exams().stream().map(ExamEntity::new).collect(Collectors.toList());
    }
}
