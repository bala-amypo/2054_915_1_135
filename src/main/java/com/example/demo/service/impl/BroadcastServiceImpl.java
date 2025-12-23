package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.BroadcastLogRepository;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.repository.SubscriptionRepository;
import com.example.demo.service.BroadcastService;

import java.util.List;

public class BroadcastServiceImpl implements BroadcastService {

    private final EventUpdateRepository updateRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final BroadcastLogRepository logRepository;

    public BroadcastServiceImpl(EventUpdateRepository updateRepository,
                                SubscriptionRepository subscriptionRepository,
                                BroadcastLogRepository logRepository) {
        this.updateRepository = updateRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.logRepository = logRepository;
    }

    @Override
    public void broadcastUpdate(Long updateId) {
        EventUpdate update = updateRepository.findById(updateId)
                .orElseThrow(() -> new RuntimeException("Update not found"));

        List<Subscription> subs = subscriptionRepository
                .findByEventId(update.getEvent().getId());

        for (Subscription s : subs) {
            BroadcastLog log = new BroadcastLog();
            log.setEventUpdate(update);
            log.setSubscriber(s.getUser());
            logRepository.save(log);
        }
    }

    @Override
    public void recordDelivery(Long updateId, Long userId, boolean success) {
        List<BroadcastLog> logs = logRepository.findByEventUpdateId(updateId);

        for (BroadcastLog log : logs) {
            if (log.getSubscriber().getId().equals(userId)) {
                log.setDeliveryStatus(success ? DeliveryStatus.SENT : DeliveryStatus.FAILED);
                logRepository.save(log);
            }
        }
    }

    @Override
    public List<BroadcastLog> getLogsForUpdate(Long updateId) {
        return logRepository.findByEventUpdateId(updateId);
    }
}
