package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Event event;

    private Instant subscribedAt;

    @PrePersist
    public void onCreate() {
        this.subscribedAt = Instant.now();
    }

    // getters & setters
}
