package com.example.demo.service.impl;

import com.example.demo.entity.EventUpdate;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.service.EventUpdateService;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventUpdateServiceImpl implements EventUpdateService {

    private final EventUpdateRepository eventUpdateRepository;

    public EventUpdateServiceImpl(EventUpdateRepository eventUpdateRepository) {
        this.eventUpdateRepository = eventUpdateRepository;
    }

    @Override
    public EventUpdate publishUpdate(EventUpdate update) {
        return eventUpdateRepository.save(update);
    }

    @Override
    public EventUpdate getUpdateById(Long id) {
        return eventUpdateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Update not found"));
    }

    @Override
    public List<EventUpdate> getUpdatesForEvent(Long eventId) {
        return eventUpdateRepository
                .findByEventIdOrderByTimestampAsc(eventId);
    }
}
