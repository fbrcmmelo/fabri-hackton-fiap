package com.fabri.srv.user.infra.config;

import com.fabri.srv.user.infra.adapters.gateway.DomainEventPubGatewayImpl;
import com.fabri.srvmessagebroker.domain.RabbitMqServiceAdapter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public RabbitMqServiceAdapter rabbitMqServiceAdapter(RabbitTemplate rabbitTemplate) {
        return new RabbitMqServiceAdapter(rabbitTemplate);
    }

    @Bean
    public DomainEventPubGatewayImpl domainEventPubGateway(ApplicationEventPublisher publisher) {
        return new DomainEventPubGatewayImpl(publisher);
    }
}
