package com.fabri.srvappointment.application.io;

import com.fabri.srvappointment.domain.vo.Exam;
import com.fabri.srvappointment.domain.vo.Medication;
import com.fabri.srvappointment.infra.adapters.controller.dto.FinishAppointmentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FinishAppointmentInput {

    private Long triageId;
    private Long doctorId;
    private String notes;
    private List<Medication> medications;
    private List<Exam> exams;

    public static FinishAppointmentInput from(FinishAppointmentRequest request) {
        return new FinishAppointmentInput(
                request.triageId(),
                request.doctorId(),
                request.notes(),
                request.medications().stream().map(Medication::from).toList(),
                request.exams().stream().map(Exam::from).toList()
        );
    }
}
