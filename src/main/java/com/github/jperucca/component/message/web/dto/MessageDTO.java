package com.github.jperucca.component.message.web.dto;

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
