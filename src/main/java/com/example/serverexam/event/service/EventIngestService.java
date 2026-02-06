package com.example.serverexam.event.service;

import com.example.serverexam.config.MonitoringProperties;
import com.example.serverexam.event.dto.EventIngestRequest;
import com.example.serverexam.event.model.Severity;
import com.example.serverexam.event.model.SystemEvent;
import com.example.serverexam.event.repository.SystemEventRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EventIngestService {

    private final SystemEventRepository systemEventRepository;
    private final MonitoringProperties monitoringProperties;
    private final BlockingQueue<SystemEvent> queue = new LinkedBlockingQueue<>();

    public EventIngestService(SystemEventRepository systemEventRepository, MonitoringProperties monitoringProperties) {
        this.systemEventRepository = systemEventRepository;
        this.monitoringProperties = monitoringProperties;
    }

    public void enqueue(EventIngestRequest request) {
        SystemEvent event = new SystemEvent();
        event.setServiceName(sanitize(request.getServiceName()));
        event.setEventType(sanitize(request.getEventType()));
        event.setSeverity(request.getSeverity() == null ? Severity.INFO : request.getSeverity());
        event.setMessage(sanitize(request.getMessage()));
        queue.offer(event);
    }

    @Scheduled(fixedDelayString = "2000")
    public void flushBatch() {
        int batchSize = monitoringProperties.getIngestBatchSize();
        List<SystemEvent> batch = new ArrayList<>(batchSize);
        queue.drainTo(batch, batchSize);
        if (!batch.isEmpty()) {
            systemEventRepository.saveAll(batch);
        }
    }

    private String sanitize(String value) {
        if (value == null || value.isBlank()) {
            return "unknown";
        }
        return value.trim();
    }
}
