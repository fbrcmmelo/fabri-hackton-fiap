package com.fabri.srvappointment.infra.config;

import com.fabri.srvmessagebroker.domain.RabbitMqServiceAdapter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    RabbitMqServiceAdapter rabbitMqServiceAdapter(RabbitTemplate rabbitTemplate) {
        return new RabbitMqServiceAdapter(rabbitTemplate);
    }
}
