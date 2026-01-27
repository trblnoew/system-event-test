package com.example.serverexam.domain.event;

import com.example.serverexam.domain.event.dto.EventResponse;
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
    public List<EventResponse> getRecentEvents() {

        return eventRepository.findTop50ByOrderByCreatedAtDesc().stream().map(
                event -> new EventResponse(
                        event.getId(),
                        event.getTitle(),
                        event.getSource(),
                        event.getMessage(),
                        event.getCreatedAt()

                )).toList();
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

    @GetMapping
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    @GetMapping("/range")
    public List<Event> getEventsByRange(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end
    ) {
        return eventRepository.findByCreatedAtBetween(start, end);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void collectEvent(@RequestBody EventRequest request) {

        Event event = new Event(
                request.getType(),
                request.getSource(),
                request.getMessage()
        );

        eventRepository.save(event);
    }
}
