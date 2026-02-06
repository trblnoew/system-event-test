package com.example.serverexam.event.repository;

import com.example.serverexam.event.model.Severity;
import com.example.serverexam.event.model.SystemEvent;
import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemEventRepository extends JpaRepository<SystemEvent, Long> {

    List<SystemEvent> findTop100ByOrderByCreatedAtDesc();

    List<SystemEvent> findByCreatedAtBetween(Instant start, Instant end);

    long countByCreatedAtBetween(Instant start, Instant end);

    long countByCreatedAtBetweenAndSeverity(Instant start, Instant end, Severity severity);
}
