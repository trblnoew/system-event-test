package com.example.serverexam.domain.event;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String source;
    private String message;
    private String severity;

    private LocalDateTime createdAt;

    protected Event() {
    }

    public Event(String type, String source, String message, String severity) {
        this.type = type;
        this.source = source;
        this.message = message;
        this.severity = severity;
        this.createdAt = LocalDateTime.now(); // 생성 시점 기록
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getSource() {
        return source;
    }

    public String getMessage() {
        return message;
    }

    public String getSeverity() {
        return severity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
