package com.fabri.srv.user.application;

import com.fabri.srv.user.application.dto.RegisterUserInput;
import com.fabri.srv.user.application.dto.UserOutput;
import com.fabri.srv.user.infra.IOUseCase;

public interface RegisterPatientUseCase extends IOUseCase<RegisterUserInput, UserOutput> {
}
