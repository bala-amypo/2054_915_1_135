public class BroadcastServiceImpl implements BroadcastService {

    private final EventUpdateRepository updateRepo;
    private final SubscriptionRepository subRepo;
    private final BroadcastLogRepository logRepo;

    public BroadcastServiceImpl(EventUpdateRepository u, SubscriptionRepository s, BroadcastLogRepository l) {
        updateRepo = u; subRepo = s; logRepo = l;
    }

    public void broadcastUpdate(Long updateId) {
        EventUpdate upd = updateRepo.findById(updateId).orElseThrow();
        for (Subscription s : subRepo.findByEventId(upd.getEvent().getId())) {
            BroadcastLog log = new BroadcastLog();
            log.setEventUpdate(upd);
            log.setSubscriber(s.getUser());
            logRepo.save(log);
        }
    }

    public List<BroadcastLog> getLogsForUpdate(Long id) {
        return logRepo.findByEventUpdateId(id);
    }

    public void recordDelivery(Long updateId, Long userId, boolean success) {
        for (BroadcastLog log : logRepo.findByEventUpdateId(updateId)) {
            if (log.getSubscriber().getId().equals(userId)) {
                log.setDeliveryStatus(success ? DeliveryStatus.SENT : DeliveryStatus.FAILED);
                logRepo.save(log);
            }
        }
    }
}
