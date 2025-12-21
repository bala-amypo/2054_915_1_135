public class EventServiceImpl implements EventService {

    private final EventRepository repo;
    private final UserRepository userRepo;

    public EventServiceImpl(EventRepository r, UserRepository u) {
        repo = r; userRepo = u;
    }

    public Event createEvent(Event e) {
        User u = userRepo.findById(e.getPublisher().getId())
                .orElseThrow();

        if (u.getRole() != Role.PUBLISHER && u.getRole() != Role.ADMIN)
            throw new RuntimeException("Only PUBLISHER or ADMIN");

        return repo.save(e);
    }

    public Event updateEvent(Long id, Event e) {
        Event existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        existing.setTitle(e.getTitle());
        existing.setDescription(e.getDescription());
        existing.setLocation(e.getLocation());
        existing.setCategory(e.getCategory());
        return repo.save(existing);
    }

    public void deactivateEvent(Long id) {
        Event e = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        e.setActive(false);
        repo.save(e);
    }

    public Event getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public List<Event> getActiveEvents() {
        return repo.findByIsActiveTrue();
    }
}
