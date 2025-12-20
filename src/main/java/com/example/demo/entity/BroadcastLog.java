package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "broadcast_logs")
public class BroadcastLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private EventUpdate eventUpdate;

    @ManyToOne
    private User subscriber;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus = DeliveryStatus.SENT;

    // getters & setters
}
