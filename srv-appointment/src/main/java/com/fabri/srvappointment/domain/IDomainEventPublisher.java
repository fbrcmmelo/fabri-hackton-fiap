package com.fabri.srvappointment.domain;

public interface IDomainEventPublisher {
    void publish(IDomainEvent event);
}
