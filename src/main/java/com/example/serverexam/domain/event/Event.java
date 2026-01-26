package com.example.serverexam.domain.event;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "system_event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EventStatus status;
    /**
     * 이벤트 유형
     * 예: ERROR, LOGIN, API_CALL
     */
    @Column(nullable = false, length = 50)
    private String type;

    /**
     * 이벤트 발생 위치
     * 예: auth-service, order-api
     */
    @Column(nullable = false, length = 100)
    private String source;

    /**
     * 이벤트 상세 메시지
     */
    @Column(nullable = false, length = 1000)
    private String message;


    /**
     * 이벤트 발생 시각 (운영 핵심 필드)
     */
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /** JPA 기본 생성자 (외부 사용 금지) */
    protected Event() {
    }

    /** 이벤트 생성자 (운영 기준 생성 시점 통제) */
    public Event(String type, String source, String message, String severity) {
        this.type = type;
        this.source = source;
        this.message = message;
        this.status = EventStatus.OPEN;
        this.createdAt = LocalDateTime.now();
    }

    /* ====== Getter ====== */

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
