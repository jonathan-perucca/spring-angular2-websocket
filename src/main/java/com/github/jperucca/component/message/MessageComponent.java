package com.github.jperucca.component.message;

import com.github.jperucca.component.message.repository.MessageRepository;
import com.github.jperucca.component.message.web.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageComponent {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageComponent(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<MessageDTO> getInitialMessages() {
        return messageRepository.findInitialMessages();
    }

    public List<MessageDTO> getServerMessages() {
        return messageRepository.getServerMessages();
    }
}
