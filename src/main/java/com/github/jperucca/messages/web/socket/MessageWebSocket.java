package com.github.jperucca.messages.web.socket;

import com.github.jperucca.messages.web.dto.MessageDTO;
import org.slf4j.Logger;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

import static java.util.Arrays.asList;
import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class MessageWebSocket {

    private static final Logger logger = getLogger(MessageWebSocket.class);
    private List<MessageDTO> messageRepository;

    public MessageWebSocket() {
        this.messageRepository = asList(
                MessageDTO.builder().content("Hello from server").build(),
                MessageDTO.builder().content("Wanna ask something over this channel ?").build(),
                MessageDTO.builder().content("Enter message and press [Send]").build()
        );
    }

    // Sends result to /app/conversations
    @SubscribeMapping("/conversations")
    public List<MessageDTO> handleConnect() {
        return messageRepository;
    }

    @MessageMapping("/conversations")
    public MessageDTO handleMessage(MessageDTO messageDTO) {
        logger.info("Message Received : {}", messageDTO);

        messageDTO.appendContent("New message: ");

        return messageDTO;
    }
}
