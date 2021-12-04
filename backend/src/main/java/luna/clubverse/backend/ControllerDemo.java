package luna.clubverse.backend;

import luna.clubverse.backend.event.controller.request.AddEventRequest;
import luna.clubverse.backend.event.controller.response.EventQueryResponseDemo;
import luna.clubverse.backend.event.enumuration.EventStatus;
import luna.clubverse.backend.event.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.validation.Valid;
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

    @RequestMapping(value ="/Create_event")
    public String createEventPage() {
        return "Create_event";
    }

    @CrossOrigin
    @PutMapping("/{clubId}/addToClub")
    //@PreAuthorize("hasAuthority('ADMIN')" +
    //        "or @authorizationLuna.authorize(authentication, 'EVENT_MANAGEMENT' , #clubId )" )
    public String addEvent(@PathVariable Long clubId, @RequestBody @Valid final AddEventRequest addEventRequest) {
        System.out.println(addEventRequest);
        eventService.addEventToClub(clubId,addEventRequest.toEvent());
        return "redirect:http://localhost:8080/event_list";// return type will be changed, except from get requests, there will be same type of response
    }


}
