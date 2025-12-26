package com.example.demo.service.impl;

import com.example.demo.entity.Event;
import com.example.demo.entity.EventUpdate;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.service.EventUpdateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventUpdateServiceImpl implements EventUpdateService {

    private final EventUpdateRepository eventUpdateRepository;
    private final EventRepository eventRepository;

    public EventUpdateServiceImpl(
            EventUpdateRepository eventUpdateRepository,
            EventRepository eventRepository
    ) {
        this.eventUpdateRepository = eventUpdateRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public EventUpdate publishUpdate(EventUpdate update) {

        if (update.getEvent() == null || update.getEvent().getId() == null) {
            throw new RuntimeException("Event is required");
        }

        Event event = eventRepository.findById(update.getEvent().getId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        update.setEvent(event);

        return eventUpdateRepository.save(update);
    }

    @Override
    public EventUpdate getUpdateById(Long id) {
        return eventUpdateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Update not found"));
    }

    @Override
    public List<EventUpdate> getUpdatesForEvent(Long eventId) {
        return eventUpdateRepository.findByEventId(eventId);
    }
}
