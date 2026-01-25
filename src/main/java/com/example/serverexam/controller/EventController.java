package com.example.serverexam.controller;

import com.example.serverexam.dto.EventRequest;
import com.example.serverexam.entity.SystemEvent;
import com.example.serverexam.repository.EventRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @PostMapping
    public void collect(@RequestBody EventRequest req) {
        eventRepository.save(
                new SystemEvent(
                        req.getSource(),
                        req.getEventType(),
                        req.getMessage()
                )
        );
    }
}
