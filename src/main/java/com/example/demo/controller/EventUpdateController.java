package com.example.demo.controller;

import com.example.demo.entity.EventUpdate;
import com.example.demo.service.EventUpdateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event-updates")
public class EventUpdateController {

    private final EventUpdateService eventUpdateService;

    public EventUpdateController(EventUpdateService eventUpdateService) {
        this.eventUpdateService = eventUpdateService;
    }

    @PostMapping
    public EventUpdate create(@RequestBody EventUpdate eventUpdate) {
        return eventUpdateService.publishUpdate(eventUpdate);
    }

    @GetMapping("/{id}")
    public EventUpdate getById(@PathVariable Long id) {
        return eventUpdateService.getUpdateById(id);
    }

    @GetMapping("/event/{eventId}")
    public List<EventUpdate> getUpdates(@PathVariable Long eventId) {
        return eventUpdateService.getUpdatesForEvent(eventId);
    }
}
