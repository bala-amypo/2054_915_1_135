package com.example.demo.service.impl;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.entity.DeliveryStatus;
import com.example.demo.entity.Event;
import com.example.demo.entity.EventUpdate;
import com.example.demo.entity.Subscription;
import com.example.demo.entity.User;
import com.example.demo.repository.BroadcastLogRepository;
import com.example.demo.repository.EventRepository;
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
    private final EventRepository eventRepository;

    public BroadcastServiceImpl(
            EventUpdateRepository eventUpdateRepository,
            SubscriptionRepository subscriptionRepository,
            BroadcastLogRepository broadcastLogRepository,
            EventRepository eventRepository
    ) {
        this.eventUpdateRepository = eventUpdateRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.broadcastLogRepository = broadcastLogRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public void broadcastUpdate(long eventUpdateId) {

        EventUpdate update = eventUpdateRepository.findById(eventUpdateId)
                .orElseThrow(() -> new RuntimeException("Update not found"));

        Event event = update.getEvent();

        List<Subscription> subscribers =
                subscriptionRepository.findAll();

        for (Subscription sub : subscribers) {

            User user = sub.getUser();

            BroadcastLog log = new BroadcastLog();
            log.setEventUpdate(update);
            log.setSubscriber(user);
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
