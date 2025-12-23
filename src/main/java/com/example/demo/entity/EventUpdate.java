package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class EventUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne
    private Event event;

    public Long getId() { return id; }
    public String getMessage() { return message; }
    public Event getEvent() { return event; }
}
