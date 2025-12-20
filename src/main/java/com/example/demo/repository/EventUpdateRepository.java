package com.example.demo.repository;

import com.example.demo.entity.EventUpdate;

import java.util.List;
import java.util.Optional;

public interface EventUpdateRepository {

    EventUpdate save(EventUpdate update);

    Optional<EventUpdate> findById(Long id);

    List<EventUpdate> findByEventId(Long eventId);

    List<EventUpdate> findByEventIdOrderByTimestampAsc(Long eventId);
}
