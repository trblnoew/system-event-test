package com.example.serverexam.event.dto;

import java.time.Instant;

public class TrafficBucketResponse {

    private Instant start;
    private Instant end;
    private int totalEvents;
    private int errorEvents;

    public TrafficBucketResponse(Instant start, Instant end, int totalEvents, int errorEvents) {
        this.start = start;
        this.end = end;
        this.totalEvents = totalEvents;
        this.errorEvents = errorEvents;
    }

    public Instant getStart() {
        return start;
    }

    public Instant getEnd() {
        return end;
    }

    public int getTotalEvents() {
        return totalEvents;
    }

    public int getErrorEvents() {
        return errorEvents;
    }
}
