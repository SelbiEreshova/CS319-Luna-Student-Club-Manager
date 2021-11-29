package luna.clubverse.backend;

import luna.clubverse.backend.event.controller.response.EventQueryResponseDemo;
import luna.clubverse.backend.event.enumuration.EventStatus;
import luna.clubverse.backend.event.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class ControllerDemo {

    private final EventService eventService;

    public ControllerDemo(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping("/home")
    public String welcome() {
        return "index";
    }

    @RequestMapping("/event_list")
    public String welcome2() {
        return "event_list";
    }

    @RequestMapping("/admin_event_list_demo")
    public String getAll( Model model) {
        List<EventQueryResponseDemo> events = eventService.getAllDemo();
        model.addAttribute("events", events);
        return "admin_event_list_demo";
    }
    @RequestMapping("/Create_event")
    public String createEventPage() {
        return "Create_event";
    }

    @CrossOrigin
    @PutMapping("/draft/{id}")
    public String draftEvent(@PathVariable Long id) {
        eventService.changeEventStatus(id, EventStatus.DRAFT);
        return "success";
    }
}
