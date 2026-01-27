package com.example.serverexam.domain.event;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "system_event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private String source;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    private LocalDateTime createdAt;

    protected Event() {}

    public Event(String title, String message, String source) {
        this.title = title;
        this.message = message;
        this.source = source;
        this.status = EventStatus.OPEN;
        this.createdAt = LocalDateTime.now();
    }

    /* ====== Getter ====== */

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
