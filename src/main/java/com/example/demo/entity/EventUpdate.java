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

    public EventUpdate() {
    }

    public EventUpdate(Event event, String updateContent, SeverityLevel severityLevel) {
        this.event = event;
        this.updateContent = updateContent;
        this.severityLevel = severityLevel;
    }

    @PrePersist
    public void onCreate() {
        if (severityLevel == null) {
            severityLevel = SeverityLevel.LOW;
        }
        this.timestamp = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public SeverityLevel getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(SeverityLevel severityLevel) {
        this.severityLevel = severityLevel;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
