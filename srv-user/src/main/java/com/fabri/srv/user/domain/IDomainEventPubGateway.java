package com.fabri.srv.user.domain;

import org.springframework.scheduling.annotation.Async;

public interface IDomainEventPubGateway {

    @Async
    void publish(final Object event);
}
