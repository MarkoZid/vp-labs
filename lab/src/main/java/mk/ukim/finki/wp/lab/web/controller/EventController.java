package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.service.EventService;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final LocationService locationService;

    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error, Model model) {
        List<Event> events = eventService.findAll();
        model.addAttribute("events", events);
        model.addAttribute("error", error);
        return "list-events";
    }

    @GetMapping("/add-form")
    public String getAddEventPage(Model model) {
        model.addAttribute("action", "/events/add");
        model.addAttribute("locations", locationService.findAll());
        return "add-event";
    }

    @PostMapping("/add")
    public String saveEvent(@RequestParam String name, @RequestParam String description, @RequestParam double popularityScore, @RequestParam Long locationId) {
        eventService.save(name, description, popularityScore, locationService.findLocationById(locationId));
        return "redirect:/events";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditEventForm(@PathVariable Long id, Model model) {
        model.addAttribute("action", "/events/edit/" + id);
        Event event = eventService.findEventById(id);
        if (event == null)
            return "redirect:/events?error=Event not found";

        model.addAttribute("event", event);
        model.addAttribute("locations", locationService.findAll());
        return "add-event";
    }

    @PostMapping("/edit/{id}")
    public String editEvent(@PathVariable Long id,
                            @RequestParam String name,
                            @RequestParam String description,
                            @RequestParam double popularityScore,
                            @RequestParam Long locationId) {
        Location location = locationService.findLocationById(locationId);
        eventService.edit(id, name, description, popularityScore, location);
        return "redirect:/events";
    }

    @PostMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
        return "redirect:/events";
    }
}
