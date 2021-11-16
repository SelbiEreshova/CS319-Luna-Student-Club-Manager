package luna.clubverse.backend.event.controller;

import luna.clubverse.backend.event.controller.reponse.EventQueryResponse;
import luna.clubverse.backend.event.controller.request.AddEventRequest;
import luna.clubverse.backend.event.controller.request.UpdateEventRequest;
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

    /**
     * Concerns:
     * When an event is added firsttime should be its status draft? After by pressing a button it can be published?
     * The validation of request should be done according to this decision
     */
    @CrossOrigin
    @PostMapping("/add") // post yeni şey eklemek için yapılır
    public String addEvent(@RequestBody @Valid final AddEventRequest addEventRequest) {
        eventService.addEvent(addEventRequest.toEvent());
        return "success"; // return type will be changed, except from get requests, there will be same type of response
    }


    @CrossOrigin
    @GetMapping("/get/{id}")
    public EventQueryResponse getEvent(@PathVariable Long id) {
        return new EventQueryResponse(eventService.getEvent(id));
    }

    @CrossOrigin
    @PutMapping("/update") // post yeni şey eklemek için yapılır
    public String updateEvent(@RequestBody @Valid final UpdateEventRequest updateEventRequest) {
        eventService.updateEvent(updateEventRequest.toEvent(),updateEventRequest.toEventID());
        return "success";
    }
    //put update etmek için kullanılır

    //delete silmek için kullanılır

    //get query işlmeleri için kullanılır


}

