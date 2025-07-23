package com.fabri.srvmessagebroker.infra.config;

import com.fabri.srvmessagebroker.infra.consts.ExchangeConstants;
import com.fabri.srvmessagebroker.infra.consts.FilaConstants;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConnectionConfig {

    private final AmqpAdmin amqpAdmin;

    public RabbitMqConnectionConfig(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    public Queue queue(String queueName) {
        return new Queue(queueName, true, false, false);
    }

    public DirectExchange exchange(String exchangeName) {
        return new DirectExchange(exchangeName);
    }

    public FanoutExchange fanoutExchange(String exchangeName) {
        return new FanoutExchange(exchangeName);
    }

    public Binding binding(Queue queue, Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }

    @PostConstruct
    public void setUpQueuesAndExchanges() {
        // Direct exchange for direct messages
        amqpAdmin.declareExchange(exchange(ExchangeConstants.DIRECT));

        // Fanout exchange for scheduled appointment
        amqpAdmin.declareExchange(fanoutExchange(ExchangeConstants.FAN_OUT));

        // Declare queues
        amqpAdmin.declareQueue(queue(FilaConstants.STARTED_PATIENT_TRIAGE));
        amqpAdmin.declareQueue(queue(FilaConstants.PATIENT_EMAIL_NOTIFICATION));
        amqpAdmin.declareQueue(queue(FilaConstants.DOCTOR_EMAIL_NOTIFICATION));
        amqpAdmin.declareQueue(queue(FilaConstants.FINISHED_PATIENT_TRIAGE));
        amqpAdmin.declareQueue(queue(FilaConstants.FINISHED_PATIENT_APPOINTMENT));

        // Bind patient and doctor queues to direct exchange for direct messages
        amqpAdmin.declareBinding(binding(queue(FilaConstants.STARTED_PATIENT_TRIAGE), exchange(ExchangeConstants.DIRECT)));
        amqpAdmin.declareBinding(binding(queue(FilaConstants.PATIENT_EMAIL_NOTIFICATION), exchange(ExchangeConstants.DIRECT)));
        amqpAdmin.declareBinding(binding(queue(FilaConstants.DOCTOR_EMAIL_NOTIFICATION), exchange(ExchangeConstants.DIRECT)));
        amqpAdmin.declareBinding(binding(queue(FilaConstants.FINISHED_PATIENT_TRIAGE), exchange(ExchangeConstants.DIRECT)));
        amqpAdmin.declareBinding(binding(queue(FilaConstants.FINISHED_PATIENT_APPOINTMENT), exchange(ExchangeConstants.DIRECT)));

    }

}
