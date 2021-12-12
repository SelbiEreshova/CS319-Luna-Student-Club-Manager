package luna.clubverse.backend.event.controller;

import luna.clubverse.backend.event.controller.response.EventListQueryResponse;
import luna.clubverse.backend.event.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    //Model kullanımı
    @RequestMapping("/admin_event_list")
    public String welcome(Model model) {
        List<EventListQueryResponse> events = eventService.getAllDemo();
        model.addAttribute("events", events);
        return "admin_event_list";
    }


    @RequestMapping("/app/club_event_list/{id}")
    public String welcome2(Model model,@PathVariable Long id) {
        List<EventListQueryResponse> eventsForClub = eventService.getEventsForClub(id);
        model.addAttribute("eventsForClub", eventsForClub);
        return "club_event_list";
    }

    @RequestMapping("/app/student_event_list")
    public String welcome3(Model model) {
        List<EventListQueryResponse> events = eventService.getAllDemo();
        model.addAttribute("events", events);
        return "student_event_list";
    };
}

