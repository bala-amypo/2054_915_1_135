package com.example.demo.controller;

import com.example.demo.entity.Subscription;
import com.example.demo.service.SubscriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/{eventId}")
    public Subscription subscribe(@PathVariable Long eventId,
                                  @RequestParam Long userId) {
        return subscriptionService.subscribe(userId, eventId);
    }

    @DeleteMapping("/{eventId}")
    public String unsubscribe(@PathVariable Long eventId,
                              @RequestParam Long userId) {
        subscriptionService.unsubscribe(userId, eventId);
        return "UNSUBSCRIBED";
    }

    @GetMapping("/user/{userId}")
    public List<Subscription> getUserSubscriptions(@PathVariable Long userId) {
        return subscriptionService.getUserSubscriptions(userId);
    }

    @GetMapping("/check")
    public boolean check(@RequestParam Long userId,
                         @RequestParam Long eventId) {
        return subscriptionService.isSubscribed(userId, eventId);
    }
}
