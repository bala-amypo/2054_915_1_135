package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.BroadcastService;

public class BroadcastServiceImpl implements BroadcastService {

    private final EventUpdateRepository updateRepo;
    private final SubscriptionRepository subRepo;
    private final BroadcastLogRepository logRepo;

    public BroadcastServiceImpl(EventUpdateRepository u, SubscriptionRepository s, BroadcastLogRepository l) {
        this.updateRepo = u;
        this.subRepo = s;
        this.logRepo = l;
    }

    public void broadcastUpdate(Long updateId) {
        EventUpdate update = updateRepo.findById(updateId)
                .orElseThrow(() -> new ResourceNotFoundException("Update not found"));

        for (Subscription s : subRepo.findByEventId(update.getEvent().getId())) {
            BroadcastLog log = new BroadcastLog();
            log.setEventUpdate(update);
            log.setSubscriber(s.getUser());
            logRepo.save(log);
        }
    }

    public void recordDelivery(Long updateId, Long userId, boolean failed) {
        for (BroadcastLog log : logRepo.findByEventUpdateId(updateId)) {
            if (log.getSubscriber().getId().equals(userId)) {
                log.setDeliveryStatus(failed ? DeliveryStatus.FAILED : DeliveryStatus.SENT);
                logRepo.save(log);
            }
        }
    }
}
