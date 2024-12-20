package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.repository.LocationRepository;
import mk.ukim.finki.wp.lab.repository.jpa.LocationRepositoryJPA;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepositoryJPA repository;

    public LocationServiceImpl(LocationRepositoryJPA repository) {
        this.repository = repository;
    }

    @Override
    public List<Location> findAll() {
        return repository.findAll();
    }

    @Override
    public Location findLocationById(Long locationId) {
        return repository.findById(locationId).orElse(null);
    }
}

