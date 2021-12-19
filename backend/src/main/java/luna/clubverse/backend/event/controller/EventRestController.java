package luna.clubverse.backend.event.controller;

import jdk.jfr.Event;
import luna.clubverse.backend.common.MessageResponse;

import luna.clubverse.backend.common.MessageType;
import luna.clubverse.backend.event.controller.request.AddEventRequest;
import luna.clubverse.backend.event.controller.request.UpdateEventRequest;
import luna.clubverse.backend.event.controller.response.EventListQueryResponse;
import luna.clubverse.backend.event.controller.response.EventQueryResponse;
import luna.clubverse.backend.event.enumuration.EventStatus;
import luna.clubverse.backend.event.service.EventService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
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
    @PutMapping("/update")
    public MessageResponse updateEvent(@RequestBody @Valid final UpdateEventRequest updateEventRequest) {

        String errorMessage = checkEventRequestDates(updateEventRequest.getStartDate(),updateEventRequest.getEndDate(),updateEventRequest.getStartTime(),updateEventRequest.getEndTime(),updateEventRequest.getRegistrationDeadline(),updateEventRequest.getReviewDeadline());

        if(!errorMessage.equals("")){
            return new MessageResponse(MessageType.ERROR,errorMessage);
        }

        eventService.updateEvent(updateEventRequest.toEvent(),updateEventRequest.toEventID());
        return new MessageResponse( MessageType.SUCCESS, "success");
    }


    @CrossOrigin
    @PutMapping("/{status}/{id}")
    public String changeStatusEvent(@PathVariable Long id, @PathVariable String status) throws Exception {

        EventStatus eventStatus;

        switch (status) {
            case "cancel" -> eventStatus = EventStatus.CANCELED;
            case "publish" -> eventStatus = EventStatus.PUBLISHED;
            case "draft" -> eventStatus = EventStatus.DRAFT;
            default -> throw new Exception("invalid status");
        }
        eventService.changeEventStatus(id, eventStatus);
        return "success";
    }


    //delete silmek için kullanılır

    //get query işlmeleri için kullanılır

    @CrossOrigin
    @PutMapping("/{clubId}/addToClub")
    @PreAuthorize("hasAuthority('ADMIN')" +
            "or @authorizationLuna.authorize(authentication, 'EVENT_MANAGEMENT' , #clubId )" )
    public MessageResponse addEvent(@PathVariable Long clubId, @RequestBody @Valid final AddEventRequest addEventRequest) {

        String errorMessage = checkEventRequestDates(addEventRequest.getStartDate(),addEventRequest.getEndDate(),addEventRequest.getStartTime(),addEventRequest.getEndTime(),addEventRequest.getRegistrationDeadline(),addEventRequest.getReviewDeadline());

        if(!errorMessage.equals("")){
            return new MessageResponse(MessageType.ERROR,errorMessage);
        }

        eventService.addEventToClub(clubId,addEventRequest.toEvent());
        return new MessageResponse(MessageType.SUCCESS,"New Event is created successfully");
    }



    @RequestMapping("/event_list")
    public String getAll( Model model) {
        List<EventListQueryResponse> events = eventService.getAllDemo();
        model.addAttribute("events", events);
        return "event_list";
    }


    @PutMapping("/{eventId}/addEnrolledStudent/{userId}")
    public MessageResponse addEnrolledStudent(@PathVariable Long eventId,@PathVariable Long userId) {
        return eventService.addEnrolledStudent(eventId, userId);
    }

    public boolean checkDate(LocalDate smallDate, LocalDate bigDate){
        return smallDate.compareTo(bigDate) > 0;
    }

    public String checkEventRequestDates(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, LocalDate registeredDeadline, LocalDate reviewDeadline){

        int startEndDate = startDate.compareTo(endDate);

       if(startEndDate == 0){
            int startEndTime =  startTime.compareTo(endTime);
            if(startEndTime >= 0){
                return "Start Date cannot be before than End Date";
            }
        }else if(startEndDate > 0){
            return "Start Date cannot be before than End Date";
        }

       // eşit olabilirler mi?

        if( checkDate(registeredDeadline, startDate)){
            return  "Start Date cannot be before than Registration Deadline";
        }

        if( checkDate(endDate, reviewDeadline)){
            return  "End Date cannot be before than Review Deadline";
        }

        return "";
    }

}

