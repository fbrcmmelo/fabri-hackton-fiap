package com.fabri.srv.user.application;

import com.fabri.srv.user.application.dto.ActivateDoctorInput;
import com.fabri.srv.user.application.dto.UserOutput;
import com.fabri.srv.user.infra.IOUseCase;

public interface ActivateDoctorUseCase extends IOUseCase<ActivateDoctorInput, UserOutput> {
}
