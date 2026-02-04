package com.example.serverexam.event.dto;

import java.util.List;

public class OverviewResponse {

    private int totalEvents;
    private int errorEvents;
    private double errorRate;
    private List<EventTypeCount> topEventTypes;

    public OverviewResponse(int totalEvents, int errorEvents, double errorRate, List<EventTypeCount> topEventTypes) {
        this.totalEvents = totalEvents;
        this.errorEvents = errorEvents;
        this.errorRate = errorRate;
        this.topEventTypes = topEventTypes;
    }

    public int getTotalEvents() {
        return totalEvents;
    }

    public int getErrorEvents() {
        return errorEvents;
    }

    public double getErrorRate() {
        return errorRate;
    }

    public List<EventTypeCount> getTopEventTypes() {
        return topEventTypes;
    }

    public static class EventTypeCount {
        private String eventType;
        private int count;

        public EventTypeCount(String eventType, int count) {
            this.eventType = eventType;
            this.count = count;
        }

        public String getEventType() {
            return eventType;
        }

        public int getCount() {
            return count;
        }
    }
}
