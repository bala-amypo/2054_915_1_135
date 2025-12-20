package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;
    private String category;

    @ManyToOne
    private User publisher;

    private boolean active = true;

    private Instant createdAt;
    private Instant lastUpdatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = Instant.now();
        this.lastUpdatedAt = Instant.now();
        this.active = true;
    }

    @PreUpdate
    public void onUpdate() {
        this.lastUpdatedAt = Instant.now();
    }

    // getters & setters
}
