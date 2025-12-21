public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository repo;
    private final UserRepository userRepo;
    private final EventRepository eventRepo;

    public SubscriptionServiceImpl(SubscriptionRepository r, UserRepository u, EventRepository e) {
        repo = r; userRepo = u; eventRepo = e;
    }

    public Subscription subscribe(Long userId, Long eventId) {
        if (repo.existsByUserIdAndEventId(userId, eventId))
            throw new RuntimeException("Already subscribed");

        Subscription s = new Subscription();
        s.setUser(userRepo.findById(userId).orElseThrow());
        s.setEvent(eventRepo.findById(eventId).orElseThrow());
        return repo.save(s);
    }

    public void unsubscribe(Long userId, Long eventId) {
        Subscription s = repo.findByUserIdAndEventId(userId, eventId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
        repo.delete(s);
    }

    public boolean isSubscribed(Long u, Long e) {
        return repo.existsByUserIdAndEventId(u, e);
    }

    public List<Subscription> getUserSubscriptions(Long u) {
        return repo.findByUserId(u);
    }
}
