package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LocationRepository {
    private final List<Location> locations;

    public LocationRepository() {
        this.locations = new ArrayList<>();
        locations.add(new Location("Main Hall", "123 Street", "500", "Large hall with stage"));
        locations.add(new Location("Conference Room", "456 Avenue", "200", "For small meetings"));
        locations.add(new Location("Outdoor Park", "789 Boulevard", "1000", "Open-air venue"));
        locations.add(new Location("Banquet Hall", "101 Plaza", "300", "Ideal for receptions"));
        locations.add(new Location("Gallery Space", "202 Square", "150", "Art exhibitions"));
    }

    public List<Location> findAll() {
        return locations;
    }

    public Location findById(Long id) {
        return locations.stream().filter(location -> location.getId().equals(id)).findFirst().orElse(null);
    }
}
