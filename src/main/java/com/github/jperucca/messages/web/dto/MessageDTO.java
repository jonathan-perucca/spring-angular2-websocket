package com.github.jperucca.messages.web.dto;

import lombok.Getter;

@Getter
public class MessageDTO {
    private String content;

    public void appendContent(String value) {
        this.content = value.concat(content);
    }
}
