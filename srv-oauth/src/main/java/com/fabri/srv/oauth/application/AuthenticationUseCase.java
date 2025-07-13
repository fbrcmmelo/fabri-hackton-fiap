package com.fabri.srv.oauth.application;

import com.fabri.srv.oauth.application.dto.AuthOutput;
import com.fabri.srv.oauth.application.dto.UserOutput;
import com.fabri.srv.oauth.infra.IOUseCase;

public interface AuthenticationUseCase extends IOUseCase<UserOutput, AuthOutput> {
}
