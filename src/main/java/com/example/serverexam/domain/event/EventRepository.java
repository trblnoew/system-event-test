package com.example.serverexam.domain.event;


import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    // 최근 이벤트 조회 (시간 기준)
    List<Event> findByCreatedAtAfter(LocalDateTime time);

    // 특정 타입 이벤트 조회 (ERROR 등)
    List<Event> findByType(String type);

    // 심각도 기준 이상 이벤트 조회
    List<Event> findBySeverityGreaterThanEqual(int severity);

    // 특정 소스 + 타입 조합
    List<Event> findBySourceAndType(String source, String type);
}
