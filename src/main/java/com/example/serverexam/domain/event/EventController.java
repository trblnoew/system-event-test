package com.example.serverexam.domain.event;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping
    public List<Event> getEvent() {
        return eventRepository.findAll();
    }

    @PostMapping
    public void collectEvent(@RequestBody EventRequest request) {

        Event event = new Event(
                request.getType(),
                request.getSource(),
                request.getMessage(),
                request.getSeverity()
        );

        eventRepository.save(event);
    }
}
