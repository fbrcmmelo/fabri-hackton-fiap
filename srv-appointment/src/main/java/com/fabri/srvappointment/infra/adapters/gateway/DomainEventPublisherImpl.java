package com.fabri.srvappointment.infra.adapters.gateway;

import com.fabri.srvappointment.domain.IDomainEvent;
import com.fabri.srvappointment.domain.IDomainEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DomainEventPublisherImpl implements IDomainEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(IDomainEvent event) {
        log.info("Publishing event {}", event);
        applicationEventPublisher.publishEvent(event);
    }
}
