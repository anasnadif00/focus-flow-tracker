package com.focusflow.adapters.kafka;

import com.focusflow.domain.event.DomainEvent;
import com.focusflow.domain.port.EventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventPublisher implements EventPublisher {

    private final KafkaTemplate<String, Object> kafka;

    public KafkaEventPublisher(KafkaTemplate<String, Object> kafka) {
        this.kafka = kafka;
    }

    @Override
    public void publish(DomainEvent e) {
        String topic = e.getClass().getSimpleName(); // es: BlockStarted, BlockCompleted
        kafka.send(topic, e);
    }
}
