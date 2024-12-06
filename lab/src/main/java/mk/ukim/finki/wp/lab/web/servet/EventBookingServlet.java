package mk.ukim.finki.wp.lab.web.servet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.service.impl.EventBookingServiceImpl;
import mk.ukim.finki.wp.lab.service.impl.EventServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "event-booking-servlet", urlPatterns = {"/eventBooking"})
public class EventBookingServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final EventBookingServiceImpl eventBookingService;
    private final EventServiceImpl eventService;

    public EventBookingServlet(SpringTemplateEngine templateEngine, EventBookingServiceImpl eventBookingService, EventServiceImpl eventService) {
        this.templateEngine = templateEngine;
        this.eventBookingService = eventBookingService;
        this.eventService = eventService;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<EventBooking> bookings = eventBookingService.listAll();

        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("bookings", bookings);
        templateEngine.process("bookingConfirmation.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String eventName = req.getParameter("event");
        String attendeeName = req.getParameter("attendeeName");
        String attendeeAddress = req.getRemoteAddr();
        int numOfTickets = Integer.parseInt(req.getParameter("numTickets"));

        eventBookingService.placeBooking(eventName, attendeeName, attendeeAddress, numOfTickets);
        resp.sendRedirect(req.getContextPath() + "/eventBooking");
    }
}
