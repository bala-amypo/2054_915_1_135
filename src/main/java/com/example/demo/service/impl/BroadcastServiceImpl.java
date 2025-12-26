package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.BroadcastLogRepository;
import com.example.demo.repository.SubscriptionRepository;
import com.example.demo.service.BroadcastService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BroadcastServiceImpl implements BroadcastService {

    private final SubscriptionRepository subscriptionRepository;
    private final BroadcastLogRepository broadcastLogRepository;

    public BroadcastServiceImpl(
            SubscriptionRepository subscriptionRepository,
            BroadcastLogRepository broadcastLogRepository
    ) {
        this.subscriptionRepository = subscriptionRepository;
        this.broadcastLogRepository = broadcastLogRepository;
    }

    @Override
    public void broadcastToSubscribers(EventUpdate update) {

        List<Subscription> subs = subscriptionRepository.findAll();

        for (Subscription sub : subs) {

            BroadcastLog log = new BroadcastLog();
            log.setEventUpdate(update);
            log.setSubscriber(sub.getUser());
            log.setDeliveryStatus(DeliveryStatus.SENT);

            broadcastLogRepository.save(log);
        }
    }

    @Override
    public List<BroadcastLog> getLogsForUpdate(long updateId) {
        return broadcastLogRepository.findByEventUpdateId(updateId);
    }

    @Override
    public BroadcastLog recordDelivery(long logId, long subscriberId, boolean delivered) {

        BroadcastLog log = broadcastLogRepository.findById(logId)
                .orElseThrow(() -> new RuntimeException("Log not found"));

        if (delivered) {
            log.setDeliveryStatus(DeliveryStatus.DELIVERED);
        } else {
            log.setDeliveryStatus(DeliveryStatus.FAILED);
        }

        return broadcastLogRepository.save(log);
    }
}
