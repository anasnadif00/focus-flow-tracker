package com.focusflow.domain.port;

import com.focusflow.domain.event.DomainEvent;

public interface EventPublisher {
    void publish(DomainEvent event);
}
