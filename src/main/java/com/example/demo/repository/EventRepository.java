package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.*;
import java.util.*;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByIsActiveTrue();
    List<Event> findByIsActiveTrueAndCategory(String category);
    List<Event> findByIsActiveTrueAndLocationContainingIgnoreCase(String location);
}
