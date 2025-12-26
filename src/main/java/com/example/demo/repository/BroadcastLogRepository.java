package com.example.demo.repository;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.entity.EventUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BroadcastLogRepository extends JpaRepository<BroadcastLog, Long> {

    List<BroadcastLog> findByEventUpdate(EventUpdate update);
}
