package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EventBookingServiceImpl implements EventBookingService {
    List<EventBooking> eventBookings;

    public EventBookingServiceImpl() {
        eventBookings = new ArrayList<>();
    }

    public List<EventBooking> listAll() {
        return eventBookings;
    }

    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        EventBooking booking = new EventBooking(eventName, attendeeName, attendeeAddress, (long) numberOfTickets);
        eventBookings.add(booking);
        return booking;
    }
}
