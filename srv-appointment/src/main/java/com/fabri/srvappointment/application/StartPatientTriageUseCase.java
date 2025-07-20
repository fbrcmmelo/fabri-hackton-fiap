package com.fabri.srvappointment.application;

import com.fabri.srvappointment.application.io.PatientTriageOutput;
import com.fabri.srvappointment.application.io.StartPatientTriageInput;
import com.fabri.srvappointment.infra.IOUseCase;

public interface StartPatientTriageUseCase extends IOUseCase<StartPatientTriageInput, PatientTriageOutput> {
}
