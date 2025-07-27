package com.fabri.srvpatienthistoryregistry.domain.vo;

import com.fabri.srvpatienthistoryregistry.infra.persistence.entity.DoctorPrescriptionEntity;

import java.util.List;

public record DoctorPrescription(String toPatientId, String doctorCrm,
                                 List<Medication> medications,
                                 List<Exam> exams, String notes) {
    public static DoctorPrescription from(DoctorPrescriptionEntity doctorPrescription) {
        if (doctorPrescription == null) {
            return null;
        }

        return new DoctorPrescription(
                doctorPrescription.getPatientId(),
                doctorPrescription.getDoctorCrm(),
                doctorPrescription.getMedications().stream().map(Medication::fromEntity).toList(),
                doctorPrescription.getExams().stream().map(Exam::fromEntity).toList(),
                doctorPrescription.getNotes()
        );
    }

}
