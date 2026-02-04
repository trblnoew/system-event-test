package com.example.serverexam.config;

import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "monitoring")
public class MonitoringProperties {

    private Duration alertWindow = Duration.ofMinutes(10);
    private double errorRateThreshold = 0.2;
    private int minimumEventsForAlert = 20;
    private int ingestBatchSize = 100;

    public Duration getAlertWindow() {
        return alertWindow;
    }

    public void setAlertWindow(Duration alertWindow) {
        this.alertWindow = alertWindow;
    }

    public double getErrorRateThreshold() {
        return errorRateThreshold;
    }

    public void setErrorRateThreshold(double errorRateThreshold) {
        this.errorRateThreshold = errorRateThreshold;
    }

    public int getMinimumEventsForAlert() {
        return minimumEventsForAlert;
    }

    public void setMinimumEventsForAlert(int minimumEventsForAlert) {
        this.minimumEventsForAlert = minimumEventsForAlert;
    }

    public int getIngestBatchSize() {
        return ingestBatchSize;
    }

    public void setIngestBatchSize(int ingestBatchSize) {
        this.ingestBatchSize = ingestBatchSize;
    }
}
