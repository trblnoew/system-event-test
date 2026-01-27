package com.example.serverexam.domain.event.dto;

import java.time.LocalDateTime;

public class EventResponse {

    private Long id;
    private String title;
    private String source;
    private String message;
    private LocalDateTime createdAt;

    public EventResponse(
            Long id,
            String title,
            String source,
            String message,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.title = title;
        this.source = source;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
