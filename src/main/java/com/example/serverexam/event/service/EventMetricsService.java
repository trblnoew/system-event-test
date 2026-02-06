package com.example.serverexam.event.service;

import com.example.serverexam.event.dto.OverviewResponse;
import com.example.serverexam.event.dto.TrafficBucketResponse;
import com.example.serverexam.event.model.Severity;
import com.example.serverexam.event.model.SystemEvent;
import com.example.serverexam.event.repository.SystemEventRepository;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class EventMetricsService {

    private final SystemEventRepository systemEventRepository;

    public EventMetricsService(SystemEventRepository systemEventRepository) {
        this.systemEventRepository = systemEventRepository;
    }

    public OverviewResponse overview(Duration window) {
        Instant end = Instant.now();
        Instant start = end.minus(window);
        List<SystemEvent> events = systemEventRepository.findByCreatedAtBetween(start, end);
        int totalEvents = events.size();
        int errorEvents = (int) events.stream().filter(event -> event.getSeverity() == Severity.ERROR).count();
        double errorRate = totalEvents == 0 ? 0.0 : (double) errorEvents / totalEvents;
        Map<String, Long> eventTypeCounts = events.stream()
            .collect(Collectors.groupingBy(SystemEvent::getEventType, Collectors.counting()));
        List<OverviewResponse.EventTypeCount> topEventTypes = eventTypeCounts.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .limit(5)
            .map(entry -> new OverviewResponse.EventTypeCount(entry.getKey(), entry.getValue().intValue()))
            .collect(Collectors.toList());
        return new OverviewResponse(totalEvents, errorEvents, errorRate, topEventTypes);
    }

    public List<TrafficBucketResponse> traffic(Duration window, Duration interval) {
        Instant end = Instant.now();
        Instant start = end.minus(window);
        List<TrafficBucketResponse> buckets = new ArrayList<>();
        Instant cursor = start;
        while (cursor.isBefore(end)) {
            Instant bucketEnd = cursor.plus(interval);
            List<SystemEvent> bucketEvents = systemEventRepository.findByCreatedAtBetween(cursor, bucketEnd);
            int total = bucketEvents.size();
            int error = (int) bucketEvents.stream().filter(event -> event.getSeverity() == Severity.ERROR).count();
            buckets.add(new TrafficBucketResponse(cursor, bucketEnd, total, error));
            cursor = bucketEnd;
        }
        return buckets;
    }
}
