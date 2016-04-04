package com.github.jperucca.component.message.web;

import com.github.jperucca.component.message.MessageComponent;
import com.github.jperucca.component.message.MessageRoute;
import com.github.jperucca.component.message.web.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MessageSubscriber {

    private final MessageComponent messageComponent;

    @Autowired
    public MessageSubscriber(MessageComponent messageComponent) {
        this.messageComponent = messageComponent;
    }

    // Sends result to /app/conversations
    @SubscribeMapping(MessageRoute.ENDPOINT)
    public List<MessageDTO> handleConnect() {
        return messageComponent.getInitialMessages();
    }
}
