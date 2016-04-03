package com.github.jperucca.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessagingTemplate {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public MessagingTemplate(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public <T> void convertAndSend(String destination, T message) {
        messagingTemplate.convertAndSend(destination, message);
    }
}
