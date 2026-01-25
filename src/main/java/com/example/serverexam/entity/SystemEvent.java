package com.example.serverexam.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "system_event")
public class SystemEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 어떤 시스템에서 발생했는지
    private String source;

    // 이벤트 타입 (ERROR, WARN, LOGIN, API_CALL 등)
    private String eventType;

    // 메시지
    @Column(length = 1000)
    private String message;

    // 발생 시각
    private LocalDateTime occurredAt;

    protected SystemEvent() {}

    public SystemEvent(String source, String eventType, String message) {
        this.source = source;
        this.eventType = eventType;
        this.message = message;
        this.occurredAt = LocalDateTime.now();
    }
}

