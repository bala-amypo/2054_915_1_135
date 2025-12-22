package com.example.demo.service;
import org.springframework.stereotype.Service;


import com.example.demo.entity.Subscription;
import java.util.List;
@Service
public interface SubscriptionService {

    Subscription subscribe(Long userId, Long eventId);

    void unsubscribe(Long userId, Long eventId);

    boolean isSubscribed(Long userId, Long eventId);

    List<Subscription> getUserSubscriptions(Long userId);
}
