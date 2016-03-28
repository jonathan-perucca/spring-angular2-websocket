package com.github.jperucca.config;

import com.github.jperucca.messages.event.SessionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Configuration
public class ChatConfig {

    private static final String LOGIN_DESTINATION = "/topic/login";

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @Bean
    public SessionHandler webSocketSessionHandler() {
        SessionHandler sessionHandler = new SessionHandler(messagingTemplate);
        sessionHandler.setLoginDestination(LOGIN_DESTINATION);
        return sessionHandler;
    }
}
