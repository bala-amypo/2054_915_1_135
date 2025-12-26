package com.example.demo.service;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.entity.EventUpdate;

import java.util.List;

public interface BroadcastService {

    void broadcastToSubscribers(EventUpdate update);

    List<BroadcastLog> getLogsForUpdate(long updateId);

    BroadcastLog recordDelivery(long logId, long subscriberId, boolean delivered);
}
