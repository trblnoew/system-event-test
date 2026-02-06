package com.example.serverexam.event.repository;

import com.example.serverexam.event.model.Alert;
import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findByCreatedAtBetweenOrderByCreatedAtDesc(Instant start, Instant end);
}
