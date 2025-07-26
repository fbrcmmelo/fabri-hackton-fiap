package com.fabri.srvappointment.domain.vo;

import com.fabri.srvappointment.infra.adapters.controller.dto.ExamRequest;
import com.fabri.srvappointment.infra.exception.DomainException;
import com.fabri.srvappointment.infra.externals.persistence.entity.ExamEntity;

public record Exam(Long id, String name, String description) {

    public Exam {
        if (name == null || name.isBlank()) {
            throw new DomainException("Exam Name cannot be null or blank");
        }
        if (description == null || description.isBlank()) {
            throw new DomainException("Exam Description cannot be null or blank");
        }
    }

    public static Exam from(ExamRequest examRequest) {
        return new Exam(null, examRequest.name(), examRequest.description());
    }

    public static Exam fromEntity(ExamEntity examEntity) {
        if (examEntity == null) {
            return null;
        }
        return new Exam(
                examEntity.getId(),
                examEntity.getName(),
                examEntity.getDescription()
        );
    }
}
