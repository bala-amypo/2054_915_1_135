package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "event_updates")
public class EventUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Event event;

    private String updateContent;

    @Enumerated(EnumType.STRING)
    private SeverityLevel severityLevel;

    private Instant timestamp;

    @PrePersist
    public void onCreate() {
        this.timestamp = Instant.now();
        if (this.severityLevel == null) {
            this.severityLevel = SeverityLevel.LOW;
        }
    }

    // getters & setters
}
