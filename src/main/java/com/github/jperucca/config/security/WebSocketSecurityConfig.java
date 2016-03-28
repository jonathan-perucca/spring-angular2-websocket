package com.github.jperucca.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages.simpMessageDestMatchers("/topic/login").denyAll()
                .anyMessage()
                .authenticated();
    }

    @Override
    protected boolean sameOriginDisabled() {
        boolean disableCSRF = true;

        return disableCSRF;
    }
}