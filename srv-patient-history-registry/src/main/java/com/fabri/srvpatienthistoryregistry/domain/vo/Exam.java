package com.fabri.srvpatienthistoryregistry.domain.vo;

import com.fabri.srvpatienthistoryregistry.infra.persistence.entity.ExamEntity;

public record Exam(Long id, String name, String description) {

    public Exam {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or blank");
        }
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
