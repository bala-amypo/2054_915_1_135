package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "broadcast_logs")
public class BroadcastLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eventUpdateId;

    private Long userId;

    private boolean delivered;

    public BroadcastLog() {}

    public Long getId() {
        return id;
    }

    public Long getEventUpdateId() {
        return eventUpdateId;
    }

    public void setEventUpdateId(Long eventUpdateId) {
        this.eventUpdateId = eventUpdateId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }
}
