package com.example.demo.service.impl;

import com.example.demo.entity.EventUpdate;
import com.example.demo.service.EventUpdateService;
import org.springframework.stereotype.Service;

@Service
public class EventUpdateServiceImpl implements EventUpdateService {

    @Override
    public EventUpdate publishUpdate(EventUpdate update) {
        return update;
    }

    @Override
    public EventUpdate getUpdateById(Long id) {
        return null;
    }
}
