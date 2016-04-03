package com.github.jperucca.component.message.repository;

import com.github.jperucca.component.message.web.dto.MessageDTO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

@Repository
public class MessageRepository {

    private List<MessageDTO> initialMessages = asList(
            MessageDTO.builder().content("Hello from server").build(),
            MessageDTO.builder().content("Wanna ask something over this channel ?").build(),
            MessageDTO.builder().content("Enter message and press [Send]").build()
    );

    private List<MessageDTO> serverMessages = new ArrayList<>();

    public List<MessageDTO> findInitialMessages() {
        return this.initialMessages;
    }

    public List<MessageDTO> getServerMessages() {
        return serverMessages.stream()
                .onClose(serverMessages::clear)
                .collect(toList());
    }

    @Scheduled(fixedDelay = 1000)
    public void generateServerMessage() {
        int aleatoryNumber = new Random(10).ints(0, 10).findFirst().getAsInt();
        MessageDTO messageDTO = MessageDTO.builder().content("Aleatory message " + aleatoryNumber).build();

        serverMessages.add(messageDTO);
    }
}
