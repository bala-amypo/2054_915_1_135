package com.example.demo.controller;

import com.example.demo.entity.EventUpdate;
import com.example.demo.service.BroadcastService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/broadcast")
public class BroadcastController {

    private final BroadcastService broadcastService;

    public BroadcastController(BroadcastService broadcastService) {
        this.broadcastService = broadcastService;
    }

    @PostMapping
    public void broadcast(@RequestBody EventUpdate eventUpdate) {
        broadcastService.broadcastToSubscribers(eventUpdate);
    }
}
