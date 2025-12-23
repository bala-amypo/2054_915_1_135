package com.example.demo.controller;

import com.example.demo.entity.Subscription;
import com.example.demo.service.SubscriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService service;

    public SubscriptionController(SubscriptionService service) {
        this.service = service;
    }

    @PostMapping("/{userId}/{eventId}")
    public Subscription subscribe(@PathVariable Long userId,
                                  @PathVariable Long eventId) {
        return service.subscribe(userId, eventId);
    }

    @DeleteMapping("/{userId}/{eventId}")
    public void unsubscribe(@PathVariable Long userId,
                            @PathVariable Long eventId) {
        service.unsubscribe(userId, eventId);
    }

    @GetMapping("/{userId}")
    public List<Subscription> list(@PathVariable Long userId) {
        return service.getUserSubscriptions(userId);
    }
}
