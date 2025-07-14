package com.fabri.srv.user.application;

import com.fabri.srv.user.application.dto.RegisterDoctorInput;
import com.fabri.srv.user.application.dto.UserOutput;
import com.fabri.srv.user.infra.IOUseCase;

public interface RegisterDoctorUseCase extends IOUseCase<RegisterDoctorInput, UserOutput> {
}
