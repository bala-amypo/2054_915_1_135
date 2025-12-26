package com.example.demo.service.impl;

import com.example.demo.entity.Event;
import com.example.demo.entity.Role;
import com.example.demo.repository.EventRepository;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> getEventsForUser(Role role) {
        if (role == Role.ADMIN || role == Role.ORGANIZER) {
            return eventRepository.findAll();
        }

        return eventRepository.findByIsActiveTrue();
    }

    @Override
    public Event deactivateEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        event.setActive(false);

        return eventRepository.save(event);
    }

    @Override
    public Event createEvent(Event event) {
        event.setActive(true);
        return eventRepository.save(event);
    }
}
