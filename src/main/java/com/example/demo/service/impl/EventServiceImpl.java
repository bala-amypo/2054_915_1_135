package com.example.demo.service.impl;

import com.example.demo.entity.Event;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EventService;

import java.util.List;

public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    // Constructor injection (tests use this directly)
    public EventServiceImpl(EventRepository eventRepository,
                            UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Event createEvent(Event event) {
        Long publisherId = event.getPublisher().getId();

        User publisher = userRepository.findById(publisherId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (publisher.getRole() != Role.PUBLISHER &&
            publisher.getRole() != Role.ADMIN) {
            throw new IllegalArgumentException("Only PUBLISHER or ADMIN can create events");
        }

        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Long id, Event updatedEvent) {
        Event existing = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        existing.setTitle(updatedEvent.getTitle());
        existing.setDescription(updatedEvent.getDescription());
        existing.setLocation(updatedEvent.getLocation());
        existing.setCategory(updatedEvent.getCategory());

        return eventRepository.save(existing);
    }

    @Override
    public Event getById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    }

    @Override
    public List<Event> getActiveEvents() {
        return eventRepository.findByIsActiveTrue();
    }

    @Override
    public void deactivateEvent(Long id) {
        Event event = getById(id);
        event.setActive(false);
        eventRepository.save(event);
    }
}
