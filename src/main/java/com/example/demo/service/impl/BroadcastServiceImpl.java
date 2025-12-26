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
    private BroadcastLogRepository broadcastLogRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private EventUpdateRepository eventUpdateRepository;

    @Override
    public void broadcastUpdate(long updateId, EventUpdate update) {
        var subscribers = subscriptionRepository.findByEventId(update.getEvent().getId());

        for (var s : subscribers) {
            BroadcastLog log = new BroadcastLog();
            log.setEventUpdate(update);
            log.setSubscriber(s);
            log.setDeliveryStatus("DELIVERED");
            broadcastLogRepository.save(log);
        }
    }

    @Override
    public BroadcastLog recordDelivery(long updateId, long userId, boolean delivered) {
        EventUpdate update = eventUpdateRepository.findById(updateId)
                .orElseThrow(() -> new RuntimeException("Update not found"));

        BroadcastLog log = new BroadcastLog();
        log.setEventUpdate(update);
        log.setDeliveryStatus(delivered ? "DELIVERED" : "FAILED");

        return broadcastLogRepository.save(log);
    }

    @Override
    public List<BroadcastLog> getLogsForUpdate(long updateId) {
        EventUpdate update = eventUpdateRepository.findById(updateId)
                .orElseThrow(() -> new RuntimeException("Update not found"));

        return broadcastLogRepository.findByEventUpdate(update);
    }
}
