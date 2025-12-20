package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.EventService;

public class EventServiceImpl implements EventService {

    private final EventRepository repo;
    private final UserRepository userRepo;

    public EventServiceImpl(EventRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    public Event createEvent(Event event) {
        User publisher = userRepo.findById(event.getPublisher().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (publisher.getRole() != Role.PUBLISHER && publisher.getRole() != Role.ADMIN) {
            throw new IllegalArgumentException("Only PUBLISHER or ADMIN can create events");
        }
        return repo.save(event);
    }

    public Event updateEvent(Long id, Event updated) {
        Event existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setLocation(updated.getLocation());
        existing.setCategory(updated.getCategory());
        return repo.save(existing);
    }

    public Event getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    }

    public Event deactivateEvent(Long id) {
        Event e = getById(id);
        e.setActive(false);
        return repo.save(e);
    }
}
