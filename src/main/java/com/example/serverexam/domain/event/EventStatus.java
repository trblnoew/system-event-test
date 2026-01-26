package com.example.serverexam.domain.event;

public enum EventStatus {
    OPEN,        // 신규 이벤트
    IN_PROGRESS, // 처리 중
    RESOLVED,    // 해결됨
    IGNORED      // 무시됨
}
