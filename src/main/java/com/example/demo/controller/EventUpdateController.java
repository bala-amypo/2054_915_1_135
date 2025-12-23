package com.example.demo.controller;

import com.example.demo.entity.EventUpdate;
import com.example.demo.service.EventUpdateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/updates")
public class EventUpdateController {

    private final EventUpdateService service;

    public EventUpdateController(EventUpdateService service) {
        this.service = service;
    }

    @GetMapping("/{eventId}")
    public List<EventUpdate> list(@PathVariable Long eventId) {
        return service.getUpdatesForEvent(eventId);
    }
}
