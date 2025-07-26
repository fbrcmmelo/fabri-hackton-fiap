package com.fabri.srvappointment.infra.externals.persistence.entity;

import com.fabri.srvappointment.domain.vo.DoctorPrescription;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_doctor_prescription")
public class DoctorPrescriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String notes;
    private Long patientId;
    private String doctorCrm;

    @ManyToMany(fetch =  FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "tb_doctor_prescription_medication",
            joinColumns = @JoinColumn(name = "doctor_prescription_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id")
    )
    private List<MedicationEntity> medications = new ArrayList<>();

    @ManyToMany(fetch =  FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "tb_doctor_prescription_exam",
            joinColumns = @JoinColumn(name = "doctor_prescription_id"),
            inverseJoinColumns = @JoinColumn(name = "exam_id")
    )
    private List<ExamEntity> exams = new ArrayList<>();

    public DoctorPrescriptionEntity(DoctorPrescription doctorPrescription) {
        if (doctorPrescription == null) {
            throw new IllegalArgumentException("Doctor prescription cannot be null");
        }
        this.id = doctorPrescription.prescriptionId();
        this.patientId = doctorPrescription.toPatientId();
        this.doctorCrm = doctorPrescription.doctorCrm();
        this.notes = doctorPrescription.notes();
        this.medications =
                doctorPrescription.medications().stream().map(MedicationEntity::new).collect(Collectors.toList());
        this.exams = doctorPrescription.exams().stream().map(ExamEntity::new).collect(Collectors.toList());
    }
}
