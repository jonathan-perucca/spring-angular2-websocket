package com.github.jperucca;

import org.slf4j.Logger;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class MessageWebSocket {

    private static final Logger logger = getLogger(MessageWebSocket.class);

    @MessageMapping("/conversations")
    public String handleMessage(String message) {
        logger.info("Message Received : {}", message);

        return "New message: " + message;
    }
}
