package com.example.demo.controller;

import com.example.demo.entity.EventUpdate;
import com.example.demo.service.EventUpdateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/updates")
public class EventUpdateController {

    private final EventUpdateService service;

    public EventUpdateController(EventUpdateService service) {
        this.service = service;
    }

    @PostMapping
    public EventUpdate publish(@RequestBody EventUpdate update) {
        return service.publishUpdate(update);
    }

    @GetMapping("/event/{eventId}")
    public List<EventUpdate> byEvent(@PathVariable Long eventId) {
        return service.getUpdatesForEvent(eventId);
    }

    @GetMapping("/{id}")
    public EventUpdate get(@PathVariable Long id) {
        return service.getUpdateById(id);
    }
}
