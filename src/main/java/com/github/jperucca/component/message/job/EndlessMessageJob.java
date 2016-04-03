package com.github.jperucca.component.message.job;

import com.github.jperucca.support.EventPublisher;
import com.github.jperucca.component.message.event.ServerPublishEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EndlessMessageJob {

    private final EventPublisher eventPublisher;

    @Autowired
    public EndlessMessageJob(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Scheduled(fixedDelay = 5000)
    public void pushServerMessage() {
        eventPublisher.publish(ServerPublishEvent::new);
    }
}
