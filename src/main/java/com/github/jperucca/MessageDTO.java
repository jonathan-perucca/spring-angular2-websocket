package com.github.jperucca;

import lombok.Getter;

@Getter
public class MessageDTO {
    private String content;

    public void appendContent(String value) {
        this.content = value.concat(content);
    }
}
