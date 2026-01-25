package com.example.serverexam.repository;

import com.example.serverexam.entity.SystemEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<SystemEvent, Long> {

    // 특정 시간 이후 발생한 이벤트 조회
    List<SystemEvent> findByOccurredAtAfter(LocalDateTime time);

    // 특정 타입 이벤트만 조회
    List<SystemEvent> findByEventType(String eventType);

    // 최근 N분간 특정 이벤트 개수
    long countByEventTypeAndOccurredAtAfter(
            String eventType,
            LocalDateTime time
    );

    // ==========================
    // 운영 관점 집계 쿼리
    // ==========================

    // 시간대별 ERROR 발생 건수 (JPQL)
    @Query("""
        select hour(e.occurredAt), count(e)
        from SystemEvent e
        where e.eventType = 'ERROR'
        group by hour(e.occurredAt)
        order by hour(e.occurredAt)
    """)
    List<Object[]> countErrorByHour();
}
