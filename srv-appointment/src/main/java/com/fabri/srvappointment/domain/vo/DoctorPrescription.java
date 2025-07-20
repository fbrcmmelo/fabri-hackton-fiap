package com.fabri.srvappointment.domain.vo;

import com.fabri.srvappointment.infra.externals.persistence.entity.DoctorPrescriptionEntity;

import java.util.List;

public record DoctorPrescription(Long prescriptionId, Long toPatientId, String doctorCrm, List<Medication> medications,
                                 List<Exam> exams, String notes) {

    public DoctorPrescription {
        if (toPatientId == null || doctorCrm == null || medications == null || medications.isEmpty() ||
                doctorCrm.isEmpty()) {
            throw new IllegalArgumentException("Invalid prescription data");
        }

    }

    public static DoctorPrescription from(DoctorPrescriptionEntity doctorPrescription) {
        if (doctorPrescription == null) {
            return null;
        }

        return new DoctorPrescription(
                doctorPrescription.getId(),
                doctorPrescription.getPatientId(),
                doctorPrescription.getDoctorCrm(),
                doctorPrescription.getMedications().stream().map(Medication::fromEntity).toList(),
                doctorPrescription.getExams().stream().map(Exam::fromEntity).toList(),
                doctorPrescription.getNotes()
        );
    }

    public String toText() {
        StringBuilder sb = new StringBuilder();
        sb.append("Prescription ID: ").append(prescriptionId).append("\n");
        sb.append("To Patient ID: ").append(toPatientId).append("\n");
        sb.append("Doctor CRM: ").append(doctorCrm).append("\n");
        sb.append("Notes: ").append(notes).append("\n");

        if (exams != null && !exams.isEmpty()) {
            sb.append("Exams:\n");
            for (Exam exam : exams) {
                sb.append("- ").append(exam.name()).append("\n");
            }
        }

        if (medications != null && !medications.isEmpty()) {
            sb.append("Medications:\n");
            for (Medication medication : medications) {
                sb.append("- ").append(medication.getName()).append(" (").append(medication.getDosage())
                        .append(", ").append(medication.getDays()).append(" days, ")
                        .append(medication.getTimesPerDay()).append(" times per day)\n");
            }
        }

        return sb.toString();
    }
}
