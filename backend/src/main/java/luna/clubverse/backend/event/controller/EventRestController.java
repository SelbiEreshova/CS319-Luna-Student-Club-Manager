package luna.clubverse.backend.event.controller;

import luna.clubverse.backend.common.MessageResponse;
import luna.clubverse.backend.common.MessageType;
import luna.clubverse.backend.event.controller.request.AddEventRequest;
import luna.clubverse.backend.event.controller.request.UpdateEventRequest;
import luna.clubverse.backend.event.controller.response.EventListQueryResponse;
import luna.clubverse.backend.event.controller.response.EventQueryResponse;
import luna.clubverse.backend.event.enumuration.EventStatus;
import luna.clubverse.backend.event.service.EventService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventRestController {

    private final EventService eventService;


    public EventRestController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Concerns:
     * When an event is added firsttime should be its status draft? After by pressing a button it can be published?
     * The validation of request should be done according to this decision
     */

    /*

    Example Request

    @CrossOrigin
    @PostMapping("/{clubId}/add") // post yeni şey eklemek için yapılır
    @PreAuthorize("hasAuthority('ADMIN')" +
            "or @authorizationLuna.authorize(authentication, 'EVENT_MANAGEMENT' , #clubId )" +
            "or @authorizationLuna.authorize(authentication, 'ADVISOR', #clubId)")
    public String addEvent2(@PathVariable Long clubId ,@RequestBody AddEventRequest addEventRequest) {
        eventService.addEvent(addEventRequest.toEvent());

        return "success"; // return type will be changed, except from get requests, there will be same type of response
    }
     */

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
    @PreAuthorize("hasAuthority('ADMIN')" +
            "or @authorizationLuna.authorize(authentication, 'EVENT_MANAGEMENT' , #clubId )" )
    public MessageResponse addEvent(@PathVariable Long clubId, @RequestBody @Valid final AddEventRequest addEventRequest) {
        eventService.addEventToClub(clubId,addEventRequest.toEvent());
        return  new MessageResponse(MessageType.SUCCESS,"New Event is created successfully"); // return type will be changed, except from get requests, there will be same type of response
    }



    @RequestMapping("/event_list")
    public String getAll( Model model) {
        List<EventListQueryResponse> events = eventService.getAllDemo();
        model.addAttribute("events", events);
        return "event_list";
    }


}

