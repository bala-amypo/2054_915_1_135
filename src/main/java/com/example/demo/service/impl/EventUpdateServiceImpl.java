package com.example.demo.service.impl;

import com.example.demo.entity.Event;
import com.example.demo.entity.EventUpdate;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.service.EventUpdateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventUpdateServiceImpl implements EventUpdateService {

    private final EventUpdateRepository eventUpdateRepository;
    private final EventRepository eventRepository;

    // constructor needed by tests
    public EventUpdateServiceImpl(EventUpdateRepository eventUpdateRepository) {
        this.eventUpdateRepository = eventUpdateRepository;
        this.eventRepository = null;
    }

    // constructor used by Spring
    public EventUpdateServiceImpl(EventUpdateRepository eventUpdateRepository,
                                  EventRepository eventRepository) {
        this.eventUpdateRepository = eventUpdateRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public EventUpdate publishUpdate(EventUpdate update) {

        if (eventRepository != null && update.getEventId() != null) {

            Event event = eventRepository.findById(update.getEventId())
                    .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

            update.setEventTitle(event.getTitle());
        }

        return eventUpdateRepository.save(update);
    }

    @Override
    public EventUpdate getUpdateById(Long id) {
        return eventUpdateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event update not found"));
    }

    @Override
    public List<EventUpdate> getUpdatesForEvent(Long eventId) {
        return eventUpdateRepository.findByEventId(eventId);
    }
}
