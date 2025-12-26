package com.example.demo.service.impl;

import com.example.demo.entity.Event;
import com.example.demo.entity.User;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    // REQUIRED FOR TESTS
    public EventServiceImpl() {
    }

    public EventServiceImpl(EventRepository eventRepository,
                            UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Event createEvent(Event event, Long publisherId) {
        if (publisherId != null) {
            Optional<User> publisherOpt = userRepository.findById(publisherId);
            publisherOpt.ifPresent(event::setPublisher);
        }

        // ensure active by default
        event.setIsActive(true);
        return eventRepository.save(event);
    }

    @Override
    public Event getById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getActiveEvents() {
        return eventRepository.findByIsActiveTrue();
    }

    @Override
    public Event updateEvent(Long id, Event updated) {
        Optional<Event> existingOpt = eventRepository.findById(id);

        if (existingOpt.isEmpty()) {
            return null;
        }

        Event existing = existingOpt.get();

        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setLocation(updated.getLocation());
        existing.setEventDate(updated.getEventDate());
        existing.setIsActive(updated.getIsActive());

        return eventRepository.save(existing);
    }

    @Override
    public Event deactivateEvent(Long id) {
        Optional<Event> existingOpt = eventRepository.findById(id);

        if (existingOpt.isEmpty()) {
            return null;
        }

        Event event = existingOpt.get();
        event.setIsActive(false);

        return eventRepository.save(event);
    }
}
