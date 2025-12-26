package com.example.demo.service;

import com.example.demo.entity.BroadcastLog;
import java.util.List;

public interface BroadcastService {

    void broadcastUpdate(Long updateId);

    List<BroadcastLog> getLogsForUpdate(long updateId);

    BroadcastLog recordDelivery(long updateId, long userId, boolean success);
}
