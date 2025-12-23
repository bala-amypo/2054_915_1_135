package com.example.demo.service.impl;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.entity.EventUpdate;
import com.example.demo.entity.Subscription;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BroadcastLogRepository;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.repository.SubscriptionRepository;
import com.example.demo.service.BroadcastService;

import java.util.List;

public class BroadcastServiceImpl implements BroadcastService {

    private final BroadcastLogRepository logRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final EventUpdateRepository updateRepository;

    public BroadcastServiceImpl(BroadcastLogRepository logRepository,
                                SubscriptionRepository subscriptionRepository,
                                EventUpdateRepository updateRepository) {
        this.logRepository = logRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.updateRepository = updateRepository;
    }

    @Override
    public void triggerBroadcast(Long updateId) {
        EventUpdate update = updateRepository.findById(updateId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Update not found"));

        List<Subscription> subs =
                subscriptionRepository.findByUserId(
                        update.getEvent().getPublisher().getId()
                );

        for (Subscription sub : subs) {
            BroadcastLog log = new BroadcastLog();
            log.setEventUpdate(update);
            log.setSubscriber(sub.getUser());
            logRepository.save(log);
        }
    }

    @Override
    public List<BroadcastLog> getLogsForUpdate(Long updateId) {
        return logRepository.findByEventUpdateId(updateId);
    }
}
