package com.fabri.srv.user.infra.adapters;

import com.fabri.srv.user.infra.adapters.gateway.IEmailGateway;
import com.fabri.srvmessagebroker.domain.RabbitMqServiceAdapter;
import com.fabri.srvmessagebroker.infra.consts.FilaConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailSenderAdapter implements IEmailGateway {

    private final RabbitMqServiceAdapter rabbitMqServiceAdapter;

    @Override
    public void sendEmail(String email, String message) {
        log.info("srv-user: Sending message to QUEUE: {}", FilaConstants.USERS_REGISTER);
        this.rabbitMqServiceAdapter.send(FilaConstants.USERS_REGISTER, new Object(){
            public String getEmail() {
                return email;
            }

            public String getMessage() {
                return message;
            }
        });
    }
}
