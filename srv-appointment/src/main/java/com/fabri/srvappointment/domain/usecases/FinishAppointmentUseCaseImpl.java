package com.fabri.srvappointment.domain.usecases;

import com.fabri.srvappointment.application.FinishAppointmentUseCase;
import com.fabri.srvappointment.application.io.FinishAppointmentInput;
import com.fabri.srvappointment.application.io.FinishAppointmentOutput;
import com.fabri.srvappointment.domain.gateway.IUserGateway;
import com.fabri.srvappointment.domain.services.AppointmentDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FinishAppointmentUseCaseImpl implements FinishAppointmentUseCase {
    
    private final AppointmentDomainService domainService;
    private final IUserGateway userGateway;

    @Override
    public FinishAppointmentOutput execute(FinishAppointmentInput input) {
        final var appointmentOnExecution = domainService.findByTriageId(input.getTriageId());
        final var doctor = userGateway.getDoctor(String.valueOf(input.getDoctorId()));
       
        appointmentOnExecution.createDoctorPrescription(
                input.getNotes(), 
                input.getMedications(),
                input.getExams(), 
                doctor
        );
        appointmentOnExecution.finishAppointment();

        var appointmentFinished = domainService.finishAppointment(appointmentOnExecution);

        return FinishAppointmentOutput.from(appointmentFinished);
    }
}
