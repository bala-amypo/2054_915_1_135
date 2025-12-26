package com.example.demo.service.impl;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.entity.Event;
import com.example.demo.entity.EventUpdate;
import com.example.demo.entity.Subscription;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BroadcastLogRepository;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.repository.SubscriptionRepository;
import com.example.demo.service.BroadcastService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BroadcastServiceImpl implements BroadcastService {

    private final EventUpdateRepository eventUpdateRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final BroadcastLogRepository broadcastLogRepository;
    private final EventRepository eventRepository;

    public BroadcastServiceImpl(EventUpdateRepository eventUpdateRepository,
                                SubscriptionRepository subscriptionRepository,
                                BroadcastLogRepository broadcastLogRepository,
                                EventRepository eventRepository) {

        this.eventUpdateRepository = eventUpdateRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.broadcastLogRepository = broadcastLogRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public BroadcastServiceImpl(
        EventUpdateRepository eventUpdateRepository,
        SubscriptionRepository subscriptionRepository,
        BroadcastLogRepository broadcastLogRepository,
        EventRepository eventRepository
) {
    this.eventUpdateRepository = eventUpdateRepository;
    this.subscriptionRepository = subscriptionRepository;
    this.broadcastLogRepository = broadcastLogRepository;
    this.eventRepository = eventRepository;
}

    }
}
