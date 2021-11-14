package luna.clubverse.backend.event.controller;

import luna.clubverse.backend.event.controller.request.AddEventRequest;
import luna.clubverse.backend.event.service.EventService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @CrossOrigin
    @PostMapping("/add")
    public String addEvent(@RequestBody AddEventRequest addEventRequest) {
        eventService.addEvent(addEventRequest.toEvent());
        return "success";
    }
}

