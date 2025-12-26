package com.example.demo.controller;

import com.example.demo.entity.Event;
import com.example.demo.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @PostMapping
    public Event create(@RequestBody Event e) {
        return service.createEvent(e);
    }

    @PutMapping("/{id}")
    public Event update(@PathVariable Long id,
                        @RequestBody Event e) {
        return service.updateEvent(id, e);
    }

    @GetMapping("/{id}")
    public Event get(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/active")
    public List<Event> active() {
        return service.getActiveEvents();
    }

    @PatchMapping("/{id}/deactivate")
    public Event deactivate(@PathVariable Long id) {
        return service.deactivateEvent(id);
    }
}
