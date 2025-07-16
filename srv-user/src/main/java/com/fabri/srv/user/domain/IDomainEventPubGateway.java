package com.fabri.srv.user.domain;

public interface IDomainEventPubGateway {
    void publish(final Object event);
}
