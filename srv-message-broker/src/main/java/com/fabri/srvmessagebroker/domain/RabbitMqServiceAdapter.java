package com.fabri.srvmessagebroker.domain;

import com.fabri.srvmessagebroker.domain.usecases.SendQueueMessageUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMqServiceAdapter implements SendQueueMessageUseCase {

    private final RabbitTemplate service;
    private final ObjectMapper mapper;

    public RabbitMqServiceAdapter(RabbitTemplate service) {
        this.service = service;
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void send(String queueName, Object message) {
        try {
            this.service.convertAndSend(queueName, mapper.writeValueAsString(message));
        } catch (AmqpException | JsonProcessingException e) {
            log.error("Erro ao tentar enviar mensagem para fila: {}: Erro: {}", queueName, e.getLocalizedMessage());
            throw new IllegalCallerException("Falha ao enviar mensagem para fila");
        }
    }

}