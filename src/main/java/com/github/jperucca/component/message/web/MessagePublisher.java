package com.github.jperucca.component.message.web;

import com.github.jperucca.component.message.MessageComponent;
import com.github.jperucca.component.message.event.ServerPublishEvent;
import com.github.jperucca.component.message.web.dto.MessageDTO;
import com.github.jperucca.support.MessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MessagePublisher {

    private final MessagingTemplate messagingTemplate;
    private final MessageComponent messageComponent;

    @Autowired
    public MessagePublisher(MessagingTemplate messagingTemplate,
                            MessageComponent messageComponent) {
        this.messagingTemplate = messagingTemplate;
        this.messageComponent = messageComponent;
    }

    @EventListener
    public void pushServerMessage(ServerPublishEvent event) {
        List<MessageDTO> serverMessages = messageComponent.getServerMessages();

        messagingTemplate.convertAndSend("/topic/conversations", serverMessages);
    }
}
