package com.fabri.srv.oauth.application;

import com.fabri.srv.oauth.application.dto.UserInput;
import com.fabri.srv.oauth.application.dto.UserOutput;
import com.fabri.srv.oauth.infra.IOUseCase;

public interface FindUserUseCase extends IOUseCase<UserInput, UserOutput> {

}
