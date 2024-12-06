package mk.ukim.finki.wp.lab.web.servet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.service.impl.EventServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "event-servlet", urlPatterns = {""})
public class EventListServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final EventServiceImpl eventService;

    public EventListServlet(SpringTemplateEngine templateEngine, EventServiceImpl eventService) {
        this.templateEngine = templateEngine;
        this.eventService = eventService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get search parameters from the request
        String searchKeyword = req.getParameter("searchKeyword");
        String desiredRatingStr = req.getParameter("desiredRating");

        List<Event> eventList;

        // Filter the events based on the provided search criteria
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            eventList = eventService.searchEvents(searchKeyword);
        } else if (desiredRatingStr != null && !desiredRatingStr.isEmpty()) {
            try {
                double desiredRating = Double.parseDouble(desiredRatingStr);
                eventList = eventService.findAll().stream()
                        .filter(event -> event.getPopularityScore() >= desiredRating)
                        .toList();
            } catch (NumberFormatException e) {
                eventList = new ArrayList<>();
            }
        } else {
            eventList = eventService.findAll();
        }

        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("events", eventList);
        templateEngine.process("list-events.html", context, resp.getWriter());
    }

}
