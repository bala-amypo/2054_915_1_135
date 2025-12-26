package com.example.demo.controller;

import com.example.demo.entity.EventUpdate;
import com.example.demo.service.BroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/broadcasts")
public class BroadcastController {

    @Autowired
    private BroadcastService broadcastService;

    @PostMapping("/{updateId}")
    public void broadcast(@PathVariable Long updateId, @RequestBody EventUpdate update) {
        broadcastService.broadcastUpdate(updateId, update);
    }
}
