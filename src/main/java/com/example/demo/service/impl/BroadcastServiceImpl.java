package com.example.demo.service.impl;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.entity.DeliveryStatus;
import com.example.demo.entity.EventUpdate;
import com.example.demo.entity.Subscription;
import com.example.demo.repository.BroadcastLogRepository;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.repository.SubscriptionRepository;
import com.example.demo.service.BroadcastService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BroadcastServiceImpl implements BroadcastService {

    private final EventUpdateRepository eventUpdateRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final BroadcastLogRepository broadcastLogRepository;

    public BroadcastServiceImpl(
            EventUpdateRepository eventUpdateRepository,
            SubscriptionRepository subscriptionRepository,
            BroadcastLogRepository broadcastLogRepository
    ) {
        this.eventUpdateRepository = eventUpdateRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.broadcastLogRepository = broadcastLogRepository;
    }

    @Override
    public void broadcastUpdate(Long updateId) {
        EventUpdate update = eventUpdateRepository.findById(updateId)
                .orElseThrow();

        List<Subscription> subs =
                subscriptionRepository.findByEventId(update.getEvent().getId());

        for (Subscription s : subs) {
            BroadcastLog log = new BroadcastLog();
            log.setEventUpdate(update);
            log.setSubscriber(s.getUser());
            broadcastLogRepository.save(log);
        }
    }

    @Override
    public List<BroadcastLog> getLogsForUpdate(Long updateId) {
        return broadcastLogRepository.findByEventUpdateId(updateId);
    }

    @Override
    public void recordDelivery(long updateId, long userId, boolean success) {
        List<BroadcastLog> logs = broadcastLogRepository.findByEventUpdateId(updateId);

        for (BroadcastLog log : logs) {
            if (log.getSubscriber().getId().equals(userId)) {
                log.setDeliveryStatus(success ? DeliveryStatus.SENT : DeliveryStatus.FAILED);
                broadcastLogRepository.save(log);
            }
        }
    }
}
