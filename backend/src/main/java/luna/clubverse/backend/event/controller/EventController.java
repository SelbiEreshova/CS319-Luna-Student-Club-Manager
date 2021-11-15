package luna.clubverse.backend.event.controller;

import luna.clubverse.backend.event.controller.request.AddEventRequest;
import luna.clubverse.backend.event.service.EventService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @CrossOrigin
    @PostMapping("/add") // post yeni şey eklemek için yapılır
    public String addEvent(@RequestBody @Valid final AddEventRequest addEventRequest) {
        eventService.addEvent(addEventRequest.toEvent());
        return "success";
    }

    //put update etmek için kullanılır

    //delete silmek için kullanılır

    //get query işlmeleri için kullanılır


}

