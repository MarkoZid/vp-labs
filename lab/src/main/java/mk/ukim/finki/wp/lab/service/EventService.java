package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EventService {
    List<Event> findAll();
    List<Event> searchEvents(String text);
    Event findEventById(Long id);
    void save(String name, String description, double popularityScore, Location location);
    void edit(Long id, String name, String description, double popularityScore, Location location);
    void delete(Long id);
    List<Event> findEventsByLocation(Long locationId);
}