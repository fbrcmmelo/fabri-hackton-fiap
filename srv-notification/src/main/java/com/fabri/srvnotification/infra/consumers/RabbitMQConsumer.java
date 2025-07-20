package com.fabri.srvnotification.infra.consumers;

public interface RabbitMQConsumer {

    void consume(String message);

}
