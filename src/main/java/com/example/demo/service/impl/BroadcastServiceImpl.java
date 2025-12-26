package com.example.demo.service.impl;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.entity.DeliveryStatus;
import com.example.demo.entity.EventUpdate;
import com.example.demo.entity.Subscription;
import com.example.demo.repository.BroadcastLogRepository;
import com.example.demo.repository.SubscriptionRepository;
import com.example.demo.service.BroadcastService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BroadcastServiceImpl implements BroadcastService {

    private final SubscriptionRepository subscriptionRepository;
    private final BroadcastLogRepository broadcastLogRepository;

    public BroadcastServiceImpl(SubscriptionRepository subscriptionRepository,
                                BroadcastLogRepository broadcastLogRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.broadcastLogRepository = broadcastLogRepository;
    }

    @Override
    public void broadcastToSubscribers(EventUpdate update) {

        List<Subscription> subscriptions =
                subscriptionRepository.findByEventId(update.getEvent().getId());

        for (Subscription subscription : subscriptions) {
            BroadcastLog log = new BroadcastLog(
                    update,
                    subscription.getUser(),
                    DeliveryStatus.SENT
            );

            broadcastLogRepository.save(log);
        }
    }
}
