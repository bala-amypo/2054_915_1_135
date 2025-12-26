package com.example.demo.service;

import com.example.demo.entity.EventUpdate;

public interface BroadcastService {

    void broadcastToSubscribers(EventUpdate update);
}
