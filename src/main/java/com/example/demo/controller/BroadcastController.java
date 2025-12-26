package com.example.demo.controller;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.service.BroadcastService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/broadcasts")
public class BroadcastController {

    private final BroadcastService service;

    public BroadcastController(BroadcastService service) {
        this.service = service;
    }

    @PostMapping("/trigger/{updateId}")
    public void trigger(@PathVariable Long updateId) {
        service.broadcastUpdate(updateId);
    }

    @GetMapping("/logs/{updateId}")
    public List<BroadcastLog> logs(@PathVariable Long updateId) {
        return service.getLogsForUpdate(updateId);
    }
}
