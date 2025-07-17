package com.fabri.srvnotification.infra.consumers.user;

import com.fabri.srvmessagebroker.infra.consts.FilaConstants;
import com.fabri.srvnotification.infra.adapters.controller.EmailRequest;
import com.fabri.srvnotification.infra.adapters.controller.UserController;
import com.fabri.srvnotification.infra.config.JsonUtils;
import com.fabri.srvnotification.infra.consumers.RabbitMqConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegisteredUserConsumer implements RabbitMqConsumer {

    private final UserController userController;
    private final JsonUtils jsonUtils;

    @Override
    @RabbitListener(queues = FilaConstants.USERS_REGISTER)
    public void consume(String message) {
        log.info("srv-notification: Consuming message from QUEUE");
        userController.notifyRegisteredUser(jsonUtils.fromJson(message, EmailRequest.class));
    }

}
