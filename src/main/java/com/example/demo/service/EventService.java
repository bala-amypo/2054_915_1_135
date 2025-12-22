package com.example.demo.service;
import org.springframework.stereotype.Service;


import com.example.demo.entity.Event;
import java.util.List;
@Service
public interface EventService {
    Event createEvent(Event event);
    Event updateEvent(Long id, Event event);
    void deactivateEvent(Long id);
    Event getById(Long id);
    List<Event> getActiveEvents();
}
