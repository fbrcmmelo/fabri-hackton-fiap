package com.fabri.srv.user.infra.adapters.gateway;

import com.fabri.srv.user.domain.IDomainEventPubGateway;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@Slf4j
@RequiredArgsConstructor
public class DomainEventPubGatewayImpl implements IDomainEventPubGateway {

    @NonNull
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(Object event) {
        log.info("Publishing event: {}", event);
        applicationEventPublisher.publishEvent(event);
    }

}
