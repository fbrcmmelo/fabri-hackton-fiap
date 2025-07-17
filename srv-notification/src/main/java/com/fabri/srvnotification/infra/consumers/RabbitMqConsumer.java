package com.fabri.srvnotification.infra.consumers;

public interface RabbitMqConsumer {

    void consume(String message);

}
