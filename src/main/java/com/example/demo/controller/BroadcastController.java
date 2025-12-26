package com.example.demo.controller;

import com.example.demo.service.BroadcastService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/broadcast")
public class BroadcastController {

    private final BroadcastService broadcastService;

    public BroadcastController(BroadcastService broadcastService) {
        this.broadcastService = broadcastService;
    }

    @PostMapping("/{updateId}")
    public void broadcast(@PathVariable Long updateId) {
        broadcastService.broadcastUpdate(updateId);
    }
}
