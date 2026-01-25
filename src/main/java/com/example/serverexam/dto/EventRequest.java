package com.example.serverexam.dto;

public class EventRequest {

    private String source;
    private String eventType;
    private String message;

    public EventRequest() {
    }

    public String getSource() {
        return source;
    }

    public String getEventType() {
        return eventType;
    }

    public String getMessage() {
        return message;
    }
}
