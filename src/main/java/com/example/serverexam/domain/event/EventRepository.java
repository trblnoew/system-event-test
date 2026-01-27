package com.example.serverexam.domain.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findTop50ByOrderByCreatedAtDesc();

    List<Event> findByStatus(EventStatus status);

    List<Event> findByStatusAndStartAtAfter(
            EventStatus status,
            LocalDateTime now
    );

    List<Event> findByCreatedAtBetween(
            LocalDateTime start,
            LocalDateTime end
    );

    List<Event> findByTitleContaining(String keyword);

    long countByStatus(EventStatus status);

    List<HourlyErrorCount> countSeverityPerHour(String severity, LocalDateTime parse, LocalDateTime parse1);
}

