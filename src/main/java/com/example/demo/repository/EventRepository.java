package com.example.demo.repository;

import com.example.demo.entity.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepository {

    Event save(Event event);

    Optional<Event> findById(Long id);

    void delete(Event event);

    List<Event> findByIsActiveTrue();

    List<Event> findByIsActiveTrueAndCategory(String category);

    List<Event> findByIsActiveTrueAndLocationContainingIgnoreCase(String location);
}
