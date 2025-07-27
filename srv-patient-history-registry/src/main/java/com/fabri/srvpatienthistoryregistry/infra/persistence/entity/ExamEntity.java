package com.fabri.srvpatienthistoryregistry.infra.persistence.entity;

import com.fabri.srvpatienthistoryregistry.domain.vo.Exam;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExamEntity {

    private String name;
    private String description;

    public ExamEntity(Exam exam) {
        if (exam == null) {
            return;
        }

        this.name = exam.name();
        this.description = exam.description();
    }
}
