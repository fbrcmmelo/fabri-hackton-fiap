package com.fabri.srv.user.application;

import com.fabri.srv.user.application.dto.UserLoginInput;
import com.fabri.srv.user.application.dto.UserOutput;
import com.fabri.srv.user.infra.IOUseCase;

public interface FindUserLoginUseCase extends IOUseCase<UserLoginInput, UserOutput> {
}
