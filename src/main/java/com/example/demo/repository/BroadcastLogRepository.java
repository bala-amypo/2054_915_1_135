package com.example.demo.repository;

import com.example.demo.entity.BroadcastLog;

import java.util.List;

public interface BroadcastLogRepository {

    BroadcastLog save(BroadcastLog log);

    List<BroadcastLog> findByEventUpdateId(Long eventUpdateId);

    List<BroadcastLog> findBySubscriberId(Long subscriberId);
}
