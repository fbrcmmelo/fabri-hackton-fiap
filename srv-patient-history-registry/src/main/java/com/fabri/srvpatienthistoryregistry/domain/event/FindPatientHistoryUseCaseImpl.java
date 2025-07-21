package com.fabri.srvpatienthistoryregistry.domain.event;

import com.fabri.srvpatienthistoryregistry.application.io.PatientHistoryOutput;
import com.fabri.srvpatienthistoryregistry.application.usecases.FindPatientHistoryUseCase;
import com.fabri.srvpatienthistoryregistry.domain.PatientHistory;
import com.fabri.srvpatienthistoryregistry.domain.services.PatientDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindPatientHistoryUseCaseImpl implements FindPatientHistoryUseCase {

    private final PatientDomainService patientDomainService;

    @Override
    public PatientHistoryOutput execute(String input) {
        List<PatientHistory> history = patientDomainService.findHistoryByPatientId(input);
        if (history.isEmpty()) {
            return new PatientHistoryOutput(input, "No history found", List.of());
        }
        var patient = history.getFirst().getPatientAppointment().getPatientTriage().getPatient();

        return PatientHistoryOutput.from(patient, history);
    }
}
