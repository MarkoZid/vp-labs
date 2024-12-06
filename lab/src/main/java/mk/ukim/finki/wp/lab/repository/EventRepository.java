package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventRepository {
    private final List<Event> events;

    public EventRepository(LocationRepository locationRepository) {
        List<Location> locations = locationRepository.findAll();

        this.events = new ArrayList<>();
        this.events.add(new Event("Music Festival", "Enjoy live music with famous artists", 10, locations.get(0)));
        this.events.add(new Event("Tech Conference", "Discover the latest in technology", 5, locations.get(1)));
        this.events.add(new Event("Art Exhibition", "Experience beautiful artworks from local artists", 4, locations.get(2)));
        this.events.add(new Event("Book Fair", "Find your next favorite book", 6, locations.get(3)));
        this.events.add(new Event("Cooking Class", "Learn to cook delicious meals", 2, locations.get(4)));
        this.events.add(new Event("Fitness Bootcamp", "Join our outdoor fitness program", 7, locations.get(0)));
        this.events.add(new Event("Photography Workshop", "Improve your photography skills", 8, locations.get(1)));
        this.events.add(new Event("Charity Run", "Run for a good cause", 9, locations.get(2)));
        this.events.add(new Event("Theater Play", "Enjoy a classical theater performance", 8, locations.get(3)));
        this.events.add(new Event("Science Fair", "Explore the wonders of science", 4, locations.get(4)));
    }

    public List<Event> findAll() {
        return events;
    }

    public List<Event> searchEvents(String text) {
        return events.stream()
                .filter(event -> event.getName().contains(text) || event.getDescription().contains(text))
                .collect(Collectors.toList());
    }

    public Event findById(Long id) {
        return events.stream().filter(event -> event.getId() == id).findFirst().orElse(null);
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void delete(Long id) {
        events.removeIf(event -> event.getId() == id);
    }
}
