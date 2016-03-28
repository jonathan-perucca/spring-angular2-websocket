package com.github.jperucca.messages.web.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class MessageDTO {
    private String content;

    public void appendContent(String value) {
        this.content = value.concat(content);
    }
}
