package com.example.demo.service.impl;

import com.example.demo.entity.EventUpdate;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.service.EventUpdateService;

import java.util.List;

public class EventUpdateServiceImpl implements EventUpdateService {

    private final EventUpdateRepository repository;

    public EventUpdateServiceImpl(EventUpdateRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EventUpdate> getUpdatesForEvent(Long eventId) {
        return repository.findByEventIdOrderByTimestampAsc(eventId);
    }
}
