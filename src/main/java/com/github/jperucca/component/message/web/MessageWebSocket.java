package com.github.jperucca.component.message.web;

import com.github.jperucca.component.message.MessageComponent;
import com.github.jperucca.component.message.web.dto.MessageDTO;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class MessageWebSocket {

    private static final Logger logger = getLogger(MessageWebSocket.class);
    private final MessageComponent messageComponent;

    @Autowired
    public MessageWebSocket(MessageComponent messageComponent) {
        this.messageComponent = messageComponent;
    }

    // Sends result to /app/conversations
    @SubscribeMapping("/conversations")
    public List<MessageDTO> handleConnect() {
        return messageComponent.getInitialMessages();
    }

    @MessageMapping("/conversations")
    public MessageDTO handleMessage(MessageDTO messageDTO) {
        logger.info("Message Received : {}", messageDTO);

        messageDTO.appendContent("New message: ");

        return messageDTO;
    }
}
