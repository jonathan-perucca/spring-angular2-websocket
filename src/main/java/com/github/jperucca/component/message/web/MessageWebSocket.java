package com.github.jperucca.component.message.web;

import com.github.jperucca.component.message.MessageRoute;
import com.github.jperucca.component.message.web.dto.MessageDTO;
import org.slf4j.Logger;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class MessageWebSocket {

    private static final Logger logger = getLogger(MessageWebSocket.class);

    @MessageMapping(MessageRoute.ENDPOINT)
    public MessageDTO handleMessage(MessageDTO messageDTO) {
        logger.info("Message Received : {}", messageDTO);

        messageDTO.appendContent("New message: ");

        return messageDTO;
    }
}
