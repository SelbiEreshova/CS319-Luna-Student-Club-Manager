package luna.clubverse.backend.event.controller;


import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.club.service.ClubService;
import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.event.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import luna.clubverse.backend.event.controller.response.EventListQueryResponse;

import java.util.List;

@Controller
public class EventController {

    private final EventService eventService;
    private final ClubService clubService;

    public EventController(EventService eventService, ClubService clubService) {
        this.eventService = eventService;
        this.clubService = clubService;
    }

    @RequestMapping("/app/open_create_event/{clubId}")
    public String getCreateEvent(@PathVariable Long clubId) {
        //eventService.addEventToClub(clubId,);
        //model.addAttribute("club", club);
        Club club = clubService.getClub(clubId);
        return "create_event";
    }

    @RequestMapping("/app/open_edit_event/{eventId}")
    public String getEditEvent( @PathVariable Long eventId) {

        //Event event = eventService.getEvent(eventId);
        //model.addAttribute("event", event);
        return "edit_event";
    }

    //Model kullanımı
    @RequestMapping("/admin_event_list")
    public String welcome(Model model) {
        List<EventListQueryResponse> events = eventService.getAllDemo();
        model.addAttribute("events", events);
        return "admin_event_list";
    }


    @RequestMapping("/app/admin_club_event_list/{id}")
    public String welcome2(Model model,@PathVariable Long id) {
        List<EventListQueryResponse> events = eventService.getEventsForClub(id);
        model.addAttribute("events", events);
        return "admin_club_event_list";
    }

    @RequestMapping("/app/student_event_list")
    public String welcome3(Model model) {
        List<EventListQueryResponse> events = eventService.getAllDemo();
        model.addAttribute("events", events);
        return "student_event_list";
    }

    @RequestMapping("/app/student_club_event_list/{id}")
    public String welcome4(Model model,@PathVariable Long id) {
        List<EventListQueryResponse> eventsForClub = eventService.getEventsForClub(id);
        model.addAttribute("eventsForClub", eventsForClub);
        return "student_club_event_list";
    }


    @RequestMapping("/admin.html")
    public String welcome5() {

        return "admin";
}


    @RequestMapping("/app/STUDENT_event_homepage/{id}")
    public String studentEventPage(Model model,@PathVariable Long id) {
        model.addAttribute("eventId", id);
        return "student_event_homepage";

    }


    @RequestMapping("/app/student_myevent_list")
    public String studentEventPage() {
       // model.addAttribute("eventId", id);
        return "student_event_list";
    }

}

