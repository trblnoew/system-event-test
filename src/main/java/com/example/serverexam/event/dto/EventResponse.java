package com.example.serverexam.event.dto;

import com.example.serverexam.event.model.Severity;
import java.time.Instant;

public class EventResponse {

    private Long id;
    private String serviceName;
    private String eventType;
    private Severity severity;
    private String message;
    private Instant createdAt;

    public EventResponse(Long id, String serviceName, String eventType, Severity severity, String message, Instant createdAt) {
        this.id = id;
        this.serviceName = serviceName;
        this.eventType = eventType;
        this.severity = severity;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getEventType() {
        return eventType;
    }

    public Severity getSeverity() {
        return severity;
    }

    public String getMessage() {
        return message;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
