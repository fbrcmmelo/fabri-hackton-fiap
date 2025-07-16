package com.fabri.srvmessagebroker.infra.config;

import com.fabri.srvmessagebroker.infra.consts.ExchangeConstants;
import com.fabri.srvmessagebroker.infra.consts.FilaConstants;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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

    public Binding binding(Queue queue, DirectExchange exchange) {
        return new Binding(queue.getName(), DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    public void setUpQueuesAndExchanges() {
        amqpAdmin.declareExchange(exchange(ExchangeConstants.DIRECT));

        // declared queues
        amqpAdmin.declareQueue(queue(FilaConstants.USERS_REGISTER));
        amqpAdmin.declareBinding(binding(queue(FilaConstants.USERS_REGISTER), exchange(ExchangeConstants.DIRECT)));
    }

}
