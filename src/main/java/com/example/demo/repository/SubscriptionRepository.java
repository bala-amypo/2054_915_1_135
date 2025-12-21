package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.*;
import java.util.*;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    boolean existsByUserIdAndEventId(Long userId, Long eventId);
    Optional<Subscription> findByUserIdAndEventId(Long userId, Long eventId);
    List<Subscription> findByUserId(Long userId);
    List<Subscription> findByEventId(Long eventId);
}
