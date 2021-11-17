package luna.clubverse.backend.event.controller;

import luna.clubverse.backend.event.controller.request.AddEventRequest;
import luna.clubverse.backend.event.controller.request.UpdateEventRequest;
import luna.clubverse.backend.event.controller.response.EventQueryResponse;
import luna.clubverse.backend.event.enumuration.EventStatus;
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

    @CrossOrigin
    @PutMapping("/cancel/{id}")
    public String cancelEvent(@PathVariable Long id) {
        eventService.changeEventStatus(id, EventStatus.CANCELED);
        return "success";
    }

    @CrossOrigin
    @PutMapping("/publish/{id}")
    public String publishEvent(@PathVariable Long id) {
        eventService.changeEventStatus(id, EventStatus.PUBLISHED);
        return "success";
    }

    @CrossOrigin
    @PutMapping("/draft/{id}")
    public String draftEvent(@PathVariable Long id) {
        eventService.changeEventStatus(id, EventStatus.DRAFT);
        return "success";
    }

    //delete silmek için kullanılır

    //get query işlmeleri için kullanılır

    @CrossOrigin
    @PutMapping("/{clubId}/addToClub")
    public String addEvent(@PathVariable Long clubId, @RequestBody @Valid final AddEventRequest addEventRequest) {
        eventService.addEventToClub(clubId,addEventRequest.toEvent());
        return "success "; // return type will be changed, except from get requests, there will be same type of response
    }



}

