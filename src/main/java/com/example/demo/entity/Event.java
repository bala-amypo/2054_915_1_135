package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;
    private String category;
    private boolean active;

    @ManyToOne
    private User publisher;

    // getters & setters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public String getCategory() { return category; }
    public boolean isActive() { return active; }
    public User getPublisher() { return publisher; }

    public void setActive(boolean active) { this.active = active; }
}
