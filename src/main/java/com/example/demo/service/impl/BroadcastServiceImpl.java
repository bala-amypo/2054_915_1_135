package com.example.demo.service.impl;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.entity.EventUpdate;
import com.example.demo.entity.User;
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
    private BroadcastLogRepository broadcastLogRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private EventUpdateRepository eventUpdateRepository;

    public BroadcastServiceImpl(){}

    @Override
    public void broadcastUpdate(long updateId, EventUpdate update) {
        List<User> subscribers =
                subscriptionRepository.findByEventId(update.getEvent().getId());

        for (User subscriber : subscribers) {
            BroadcastLog log = new BroadcastLog();
            log.setEventUpdate(update);
            log.setSubscriber(subscriber);
            log.setDeliveryStatus("DELIVERED");

            broadcastLogRepository.save(log);
        }
    }

    @Override
    public void recordDelivery(long updateId, long userId, boolean delivered) {
        EventUpdate update = eventUpdateRepository.findById(updateId)
                .orElseThrow(() -> new RuntimeException("Update not found"));

        BroadcastLog log = new BroadcastLog();
        log.setEventUpdate(update);
        log.setDeliveryStatus(delivered ? "DELIVERED" : "FAILED");

        broadcastLogRepository.save(log);
    }

    @Override
    public List<BroadcastLog> getLogsForUpdate(long updateId) {
        EventUpdate update = eventUpdateRepository.findById(updateId)
                .orElseThrow(() -> new RuntimeException("Update not found"));

        return broadcastLogRepository.findByEventUpdate(update);
    }
}
