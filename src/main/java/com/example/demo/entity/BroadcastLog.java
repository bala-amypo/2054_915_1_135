package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class BroadcastLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private EventUpdate eventUpdate;

    @ManyToOne
    private User subscriber;

    // PENDING / SENT / FAILED
    private String deliveryStatus;

    private Timestamp sentAt;

    @PrePersist
    protected void onCreate() {
        this.sentAt = new Timestamp(System.currentTimeMillis());
        if (this.deliveryStatus == null) {
            this.deliveryStatus = "SENT";
        }
    }

    // getters & setters

    public Long getId() { return id; }

    public EventUpdate getEventUpdate() { return eventUpdate; }
    public void setEventUpdate(EventUpdate eventUpdate) { this.eventUpdate = eventUpdate; }

    public User getSubscriber() { return subscriber; }
    public void setSubscriber(User subscriber) { this.subscriber = subscriber; }

    public String getDeliveryStatus() { return deliveryStatus; }
    public void setDeliveryStatus(String deliveryStatus) { this.deliveryStatus = deliveryStatus; }

    public Timestamp getSentAt() { return sentAt; }
}
