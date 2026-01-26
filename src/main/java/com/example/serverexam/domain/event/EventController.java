package com.example.serverexam.domain.event;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/recent")
    public List<Event> getRecentEvents() {
        return eventRepository.findTop50ByOrderByCreatedAtDesc();
    }

    @GetMapping("/stats/errors/hourly")
    public List<HourlyErrorCount> getHourlyErrorStats(
            @RequestParam String severity,
            @RequestParam String start,
            @RequestParam String end
    ) {
        return eventRepository.countSeverityPerHour(
                severity,
                LocalDateTime.parse(start),
                LocalDateTime.parse(end)
        );
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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
