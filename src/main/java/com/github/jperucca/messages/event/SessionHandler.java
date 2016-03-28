package com.github.jperucca.messages.event;

import org.slf4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import static org.slf4j.LoggerFactory.getLogger;

public class SessionHandler {

    private static final Logger logger = getLogger(SessionHandler.class);

    private final SimpMessagingTemplate messagingTemplate;
    private String loginDestination;

    public SessionHandler(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void handleConnect(SessionConnectEvent event) {
        logger.info("New User Connexion on websocket : {}", event);
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());

        String username = headerAccessor.getUser().getName();

        logger.info("Tried to extract username : {}", username);
    }

    public void setLoginDestination(String loginDestination) {
        this.loginDestination = loginDestination;
    }
}
