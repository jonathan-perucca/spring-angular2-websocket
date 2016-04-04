package com.github.jperucca.component.message;

public class MessageRoute {

    public static final String ENDPOINT = "/conversations";
    public static final String CONVERSATION_BROKER_ROUTE = "/topic".concat(ENDPOINT);
    public static final String CONVERSATION_APP_ROUTE = "/app".concat(ENDPOINT);
}
