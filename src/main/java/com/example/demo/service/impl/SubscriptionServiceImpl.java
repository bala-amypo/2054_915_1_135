package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.SubscriptionService;

import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository repo;
    private final UserRepository userRepo;
    private final EventRepository eventRepo;

    public SubscriptionServiceImpl(SubscriptionRepository r, UserRepository u, EventRepository e) {
        this.repo = r;
        this.userRepo = u;
        this.eventRepo = e;
    }

    public Subscription subscribe(Long userId, Long eventId) {
        if (repo.existsByUserIdAndEventId(userId, eventId)) {
            throw new IllegalArgumentException("Already subscribed");
        }
        User u = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Event e = eventRepo.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
        Subscription s = new Subscription();
        s.setUser(u);
        s.setEvent(e);
        return repo.save(s);
    }

    public void unsubscribe(Long userId, Long eventId) {
        Subscription s = repo.findByUserIdAndEventId(userId, eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not found"));
        repo.delete(s);
    }

    public boolean isSubscribed(Long userId, Long eventId) {
        return repo.existsByUserIdAndEventId(userId, eventId);
    }

    public List<Subscription> getUserSubscriptions(Long userId) {
        return repo.findByUserId(userId);
    }
}
