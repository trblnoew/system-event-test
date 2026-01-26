package com.example.serverexam.domain.event;

public class HourlyErrorCount {

    private final String hour;
    private final long count;

    public HourlyErrorCount(String hour, long count) {
        this.hour = hour;
        this.count = count;
    }

    public String getHour() {
        return hour;
    }

    public long getCount() {
        return count;
    }
}
