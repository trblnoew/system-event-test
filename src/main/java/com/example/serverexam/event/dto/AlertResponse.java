package com.example.serverexam.event.dto;

import java.time.Instant;

public class AlertResponse {

    private Long id;
    private String message;
    private double errorRate;
    private int totalEvents;
    private Instant createdAt;

    public AlertResponse(Long id, String message, double errorRate, int totalEvents, Instant createdAt) {
        this.id = id;
        this.message = message;
        this.errorRate = errorRate;
        this.totalEvents = totalEvents;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public double getErrorRate() {
        return errorRate;
    }

    public int getTotalEvents() {
        return totalEvents;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
