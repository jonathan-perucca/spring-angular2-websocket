package com.github.jperucca.component.message.web;

import com.github.jperucca.component.message.MessageComponent;
import com.github.jperucca.component.message.event.ServerPublishEvent;
import com.github.jperucca.component.message.web.dto.MessageDTO;
import com.github.jperucca.support.MessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import java.util.List;

import static com.github.jperucca.component.message.MessageRoute.CONVERSATION_BROKER_ROUTE;

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

        messagingTemplate.convertAndSend(CONVERSATION_BROKER_ROUTE, serverMessages);
    }
}
