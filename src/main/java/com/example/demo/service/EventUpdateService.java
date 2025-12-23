package com.example.demo.service;

import com.example.demo.entity.EventUpdate;

public interface EventUpdateService {
    EventUpdate publishUpdate(EventUpdate update);
    EventUpdate getUpdateById(Long id);
}
