package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.repository.EventRepository;
import mk.ukim.finki.wp.lab.repository.jpa.EventRepositoryJPA;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepositoryJPA repository;

    public EventServiceImpl(EventRepositoryJPA repository) {
        this.repository = repository;
    }

    @Override
    public List<Event> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return repository.searchEvents(text);
    }

    @Override
    public Event findEventById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void save(String name, String description, double popularityScore, Location location) {
        repository.save(new Event(name, description, popularityScore, location));
    }

    @Override
    public void edit(Long id, String name, String description, double popularityScore, Location location) {
        Event event = repository.findById(id).orElse(null);
        event.setName(name);
        event.setDescription(description);
        event.setPopularityScore(popularityScore);
        event.setLocation(location);
        repository.save(event);
    }

    @Override
    public void delete(Long id) {
        Event event = repository.findById(id).orElse(null);
        repository.delete(event);
    }

    @Override
    public List<Event> findEventsByLocation(Long locationId) {
        return repository.findAllByLocation_Id(locationId);
    }
}
