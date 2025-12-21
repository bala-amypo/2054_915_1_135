package com.example.demo.service;
import org.springframework.stereotype.Service;


import com.example.demo.entity.BroadcastLog;
import java.util.List;

public interface BroadcastService {
    void broadcastUpdate(Long updateId);
    List<BroadcastLog> getLogsForUpdate(Long updateId);
    void recordDelivery(Long updateId, Long userId, boolean success);
}
