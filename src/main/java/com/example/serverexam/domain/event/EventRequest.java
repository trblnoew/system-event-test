package com.example.serverexam.domain.event;

public class EventRequest {

    private String type;
    private String source;
    private String message;

    // 기본 생성자 (Spring이 JSON → 객체 변환할 때 필요)
    public EventRequest() {
    }

    public String getType() {
        return type;
    }

    public String getSource() {
        return source;
    }

    public String getMessage() {
        return message;
    }

}
