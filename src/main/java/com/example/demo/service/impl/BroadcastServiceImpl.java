package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.BroadcastLogRepository;
import com.example.demo.repository.SubscriptionRepository;
import com.example.demo.service.BroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BroadcastServiceImpl implements BroadcastService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private BroadcastLogRepository broadcastLogRepository;

    @Override
    public void broadcastUpdate(long eventUpdateId, EventUpdate update) {

        List<Subscription> subscribers =
                subscriptionRepository.findByEventId(update.getEvent().getId());

        for (Subscription s : subscribers) {
            recordDelivery(eventUpdateId, s.getUser().getId(), true);
        }
    }

    @Override
    public BroadcastLog recordDelivery(long eventUpdateId, long userId, boolean delivered) {

        BroadcastLog log = new BroadcastLog();
        log.setEventUpdateId(eventUpdateId);
        log.setUserId(userId);
        log.setDelivered(delivered);

        return broadcastLogRepository.save(log);
    }

    @Override
    public List<BroadcastLog> getLogsForUpdate(long eventUpdateId) {
        return broadcastLogRepository.findByEventUpdateId(eventUpdateId);
    }
}
