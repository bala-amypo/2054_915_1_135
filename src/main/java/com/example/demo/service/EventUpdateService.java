package com.example.demo.service;

import com.example.demo.entity.EventUpdate;


public interface EventUpdateService {
    List<EventUpdate> getUpdatesForEvent(Long eventId);
}
