package com.example.demo.controller;

import com.example.demo.entity.Subscription;
import com.example.demo.service.SubscriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService service;

    public SubscriptionController(SubscriptionService service) {
        this.service = service;
    }

    @PostMapping("/{eventId}")
    public Subscription subscribe(@RequestParam Long userId,
                                  @PathVariable Long eventId) {
        return service.subscribe(userId, eventId);
    }

    @DeleteMapping("/{eventId}")
    public void unsubscribe(@RequestParam Long userId,
                            @PathVariable Long eventId) {
        service.unsubscribe(userId, eventId);
    }

    @GetMapping("/user/{userId}")
    public List<Subscription> userSubs(@PathVariable Long userId) {
        return service.getUserSubscriptions(userId);
    }

    @GetMapping("/check/{userId}/{eventId}")
    public boolean check(@PathVariable Long userId,
                         @PathVariable Long eventId) {
        return service.isSubscribed(userId, eventId);
    }
}
