package com.example.serverexam.event.controller;

import com.example.serverexam.event.dto.AlertResponse;
import com.example.serverexam.event.dto.EventIngestRequest;
import com.example.serverexam.event.dto.EventResponse;
import com.example.serverexam.event.dto.OverviewResponse;
import com.example.serverexam.event.dto.TrafficBucketResponse;
import com.example.serverexam.event.model.Alert;
import com.example.serverexam.event.model.SystemEvent;
import com.example.serverexam.event.repository.AlertRepository;
import com.example.serverexam.event.repository.SystemEventRepository;
import com.example.serverexam.event.service.EventIngestService;
import com.example.serverexam.event.service.EventMetricsService;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    private final EventIngestService eventIngestService;
    private final EventMetricsService eventMetricsService;
    private final SystemEventRepository systemEventRepository;
    private final AlertRepository alertRepository;

    public EventController(EventIngestService eventIngestService, EventMetricsService eventMetricsService,
                           SystemEventRepository systemEventRepository, AlertRepository alertRepository) {
        this.eventIngestService = eventIngestService;
        this.eventMetricsService = eventMetricsService;
        this.systemEventRepository = systemEventRepository;
        this.alertRepository = alertRepository;
    }

    @PostMapping("/api/events")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void ingestEvent(@RequestBody EventIngestRequest request) {
        eventIngestService.enqueue(request);
    }

    @GetMapping("/api/events/recent")
    public List<EventResponse> recentEvents() {
        return systemEventRepository.findTop100ByOrderByCreatedAtDesc().stream()
            .map(this::toEventResponse)
            .collect(Collectors.toList());
    }

    @GetMapping("/api/metrics/overview")
    public OverviewResponse overview(@RequestParam(defaultValue = "60") long minutes) {
        return eventMetricsService.overview(Duration.ofMinutes(minutes));
    }

    @GetMapping("/api/metrics/traffic")
    public List<TrafficBucketResponse> traffic(@RequestParam(defaultValue = "60") long minutes,
                                               @RequestParam(defaultValue = "5") long intervalMinutes) {
        return eventMetricsService.traffic(Duration.ofMinutes(minutes), Duration.ofMinutes(intervalMinutes));
    }

    @GetMapping("/api/alerts/recent")
    public List<AlertResponse> recentAlerts(@RequestParam(defaultValue = "120") long minutes) {
        Instant end = Instant.now();
        Instant start = end.minus(Duration.ofMinutes(minutes));
        return alertRepository.findByCreatedAtBetweenOrderByCreatedAtDesc(start, end).stream()
            .map(this::toAlertResponse)
            .collect(Collectors.toList());
    }

    private EventResponse toEventResponse(SystemEvent event) {
        return new EventResponse(event.getId(), event.getServiceName(), event.getEventType(), event.getSeverity(),
            event.getMessage(), event.getCreatedAt());
    }

    private AlertResponse toAlertResponse(Alert alert) {
        return new AlertResponse(alert.getId(), alert.getMessage(), alert.getErrorRate(), alert.getTotalEvents(),
            alert.getCreatedAt());
    }
}
