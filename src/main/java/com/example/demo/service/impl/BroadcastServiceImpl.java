package com.example.demo.service.impl;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.entity.EventUpdate;
import com.example.demo.repository.BroadcastLogRepository;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.repository.SubscriptionRepository;
import com.example.demo.service.BroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BroadcastServiceImpl implements BroadcastService {

    @Autowired
    private EventUpdateRepository eventUpdateRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private BroadcastLogRepository broadcastLogRepository;

    // REQUIRED BY TESTS
    public BroadcastServiceImpl() {}

    @Override
    public void broadcastUpdate(long eventId, EventUpdate update) {
        var subs = subscriptionRepository.findByEventId(eventId);

        for (var s : subs) {
            BroadcastLog log = new BroadcastLog();
            log.setEventUpdate(update);
            log.setSubscriber(s.getUser());
            log.setDeliveryStatus("DELIVERED");

            broadcastLogRepository.save(log);
        }
    }

    @Override
    public List<BroadcastLog> getLogsForUpdate(long eventUpdateId) {
        return broadcastLogRepository.findByEventUpdateId(eventUpdateId);
    }
}
