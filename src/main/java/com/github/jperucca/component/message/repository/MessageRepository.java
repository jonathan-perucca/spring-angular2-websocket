package com.github.jperucca.component.message.repository;

import com.github.jperucca.component.message.web.dto.MessageDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.github.jperucca.component.message.repository.MessageRepository.ThreadLocalAleatory.random;
import static java.lang.String.valueOf;
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
        List<MessageDTO> messageDTOs = serverMessages.stream().collect(toList());
        serverMessages.clear();
        return messageDTOs;
    }

    public void generateServerMessage() {
        int maxServerMessage = 5;
        if (serverMessages.size() < maxServerMessage) {
            int aleatoryMin = 0, aleatoryMax = 10;

            MessageDTO messageDTO = MessageDTO.builder()
                    .content("Aleatory message ".concat(valueOf(random(aleatoryMin, aleatoryMax))))
                    .build();

            serverMessages.add(messageDTO);
        }
    }

    static class ThreadLocalAleatory {
        public static int random(int min, int max) {
            return ThreadLocalRandom.current().nextInt(min, max + 1);
        }
    }
}
