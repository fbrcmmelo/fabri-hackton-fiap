package com.fabri.srvpatienthistoryregistry.infra.persistence.entity;

import com.fabri.srvpatienthistoryregistry.domain.vo.Exam;
import lombok.Getter;

@Getter
public class ExamEntity {

    private final Long id;
    private final String name;
    private final String description;

    public ExamEntity(Exam exam) {
        if (exam == null) {
            throw new IllegalArgumentException("Exam cannot be null");
        }
        this.id = exam.id();
        this.name = exam.name();
        this.description = exam.description();
    }
}
