package com.example.demo.service;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.entity.EventUpdate;

import java.util.List;

public interface BroadcastService {

    void broadcastUpdate(long updateId, EventUpdate update);

    BroadcastLog recordDelivery(long updateId, long userId, boolean delivered);

    List<BroadcastLog> getLogsForUpdate(long updateId);
}
