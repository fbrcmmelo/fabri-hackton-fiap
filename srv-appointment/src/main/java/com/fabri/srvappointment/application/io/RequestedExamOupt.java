package com.fabri.srvappointment.application.io;

import com.fabri.srvappointment.domain.vo.Exam;

public record RequestedExamOupt(String name, String description) {

    public static RequestedExamOupt from(Exam exam) {
        return new RequestedExamOupt(exam.name(), exam.description());
    }
}
