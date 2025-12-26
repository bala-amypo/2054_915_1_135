package com.example.demo.service;

import com.example.demo.entity.EventUpdate;
import com.example.demo.entity.BroadcastLog;

import java.util.List;

public interface BroadcastService {

    void broadcastUpdate(long eventUpdateId, EventUpdate update);

    BroadcastLog recordDelivery(long eventUpdateId, long userId, boolean delivered);

    List<BroadcastLog> getLogsForUpdate(long eventUpdateId);
}
