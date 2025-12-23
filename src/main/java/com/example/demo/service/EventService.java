package com.example.demo.service;

import com.example.demo.entity.Event;

import java.util.List;

public interface EventService {

    Event createEvent(Event event);

    Event updateEvent(Long id, Event updated);

    void deactivateEvent(Long id);

    List<Event> getActiveEvents();

    Event getById(Long id);
}
