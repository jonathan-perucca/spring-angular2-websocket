package com.github.jperucca.component.message.job;

import com.github.jperucca.component.message.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ServerMessageGeneratorJob {

    public static final int EVERY_100_MS = 100;
    private final MessageRepository messageRepository;

    @Autowired
    public ServerMessageGeneratorJob(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Scheduled(fixedDelay = EVERY_100_MS)
    public void generateServerMessage() {
        messageRepository.generateServerMessage();
    }

}
