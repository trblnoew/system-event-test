package com.example.serverexam.event.service;

import com.example.serverexam.config.MonitoringProperties;
import com.example.serverexam.event.model.Alert;
import com.example.serverexam.event.model.Severity;
import com.example.serverexam.event.repository.AlertRepository;
import com.example.serverexam.event.repository.SystemEventRepository;
import java.time.Instant;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

    private final MonitoringProperties monitoringProperties;
    private final SystemEventRepository systemEventRepository;
    private final AlertRepository alertRepository;

    public AlertService(MonitoringProperties monitoringProperties, SystemEventRepository systemEventRepository,
                        AlertRepository alertRepository) {
        this.monitoringProperties = monitoringProperties;
        this.systemEventRepository = systemEventRepository;
        this.alertRepository = alertRepository;
    }

    @Scheduled(fixedDelayString = "60000")
    public void evaluateAlerts() {
        Instant end = Instant.now();
        Instant start = end.minus(monitoringProperties.getAlertWindow());
        long total = systemEventRepository.countByCreatedAtBetween(start, end);
        if (total < monitoringProperties.getMinimumEventsForAlert()) {
            return;
        }
        long errors = systemEventRepository.countByCreatedAtBetweenAndSeverity(start, end, Severity.ERROR);
        double errorRate = total == 0 ? 0.0 : (double) errors / total;
        if (errorRate >= monitoringProperties.getErrorRateThreshold()) {
            Alert alert = new Alert();
            alert.setErrorRate(errorRate);
            alert.setTotalEvents((int) total);
            alert.setMessage(String.format("Error rate %.2f%% over last %d minutes",
                errorRate * 100, monitoringProperties.getAlertWindow().toMinutes()));
            alertRepository.save(alert);
        }
    }
}
