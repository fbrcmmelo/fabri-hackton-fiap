package com.fabri.srvnotification.infra.consumers.user;

import com.fabri.srvmessagebroker.infra.consts.FilaConstants;
import com.fabri.srvnotification.infra.adapters.controller.EmailController;
import com.fabri.srvnotification.infra.adapters.controller.EmailRequest;
import com.fabri.srvnotification.infra.config.JsonUtils;
import com.fabri.srvnotification.infra.consumers.RabbitMQConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DoctorNotificationConsumer implements RabbitMQConsumer {

    private final EmailController emailController;
    private final JsonUtils jsonUtils;

    @Override
    @RabbitListener(queues = FilaConstants.DOCTOR_EMAIL_NOTIFICATION)
    public void consume(String message) {
        log.info("srv-notification: Consuming body from doctor QUEUE");
        emailController.send(jsonUtils.fromJson(message, EmailRequest.class));
    }

}
