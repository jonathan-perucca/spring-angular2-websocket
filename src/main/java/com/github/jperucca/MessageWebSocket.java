package com.github.jperucca;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MessageWebSocket {

    @MessageMapping("/conversations")
    public String handleMessage(String message) {
        return "New message: " + message;
    }
}
