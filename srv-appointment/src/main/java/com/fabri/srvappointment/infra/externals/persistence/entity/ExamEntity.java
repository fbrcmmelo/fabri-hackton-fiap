package com.fabri.srvappointment.infra.externals.persistence.entity;

import com.fabri.srvappointment.domain.vo.Exam;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_prescription_exam")
public class ExamEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "exams")
    private Set<DoctorPrescriptionEntity> doctorPrescription;

    public ExamEntity(Exam exam) {
        if (exam == null) {
            throw new IllegalArgumentException("Exam cannot be null");
        }
        this.id = exam.id();
        this.name = exam.name();
        this.description = exam.description();
    }
}
