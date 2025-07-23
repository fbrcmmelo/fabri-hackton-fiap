package com.fabri.srv.user.domain.user.usecases;

import com.fabri.srv.user.application.FindUserByIdUseCase;
import com.fabri.srv.user.application.dto.UserOutput;
import com.fabri.srv.user.domain.user.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindUserByIdUseCaseImpl implements FindUserByIdUseCase {

    private final UserGateway userGateway;

    @Override
    public UserOutput execute(String input) {
        long userId = Long.parseLong(input);
        return UserOutput.fromDomain(userGateway.findById(userId));
    }
}
