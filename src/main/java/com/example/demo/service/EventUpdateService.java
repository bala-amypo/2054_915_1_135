package com.example.demo.service;
import org.springframework.stereotype.Service;


import com.example.demo.entity.EventUpdate;
import java.util.List;
@Service
public interface EventUpdateService {

    EventUpdate publishUpdate(EventUpdate update);

    EventUpdate getUpdateById(Long id);

    List<EventUpdate> getUpdatesForEvent(Long eventId);
}
