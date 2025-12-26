package com.example.demo.service.impl;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.entity.EventUpdate;
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
    public void broadcastToSubscribers(EventUpdate update) {

        var subs = subscriptionRepository.findAll();

        for (var s : subs) {
            if (s.getEvent() != null &&
                update.getEvent() != null &&
                s.getEvent().getId().equals(update.getEvent().getId())) {

                BroadcastLog log = new BroadcastLog();
                log.setEventUpdate(update);
                log.setSubscriber(s.getUser());
                log.setDelivered(true);

                broadcastLogRepository.save(log);
            }
        }
    }

    @Override
    public List<BroadcastLog> getLogsForUpdate(long updateId) {
        return broadcastLogRepository.findByEventUpdateId(updateId);
    }

    @Override
    public BroadcastLog recordDelivery(long updateId, long userId, boolean delivered) {

        EventUpdate update = eventUpdateRepository.findById(updateId)
                .orElseThrow(() -> new RuntimeException("Update not found"));

        BroadcastLog log = new BroadcastLog();
        log.setEventUpdate(update);
        log.setDelivered(delivered);

        return broadcastLogRepository.save(log);
    }
}
