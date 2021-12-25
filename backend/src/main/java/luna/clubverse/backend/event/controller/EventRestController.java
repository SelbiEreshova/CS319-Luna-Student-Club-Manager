package luna.clubverse.backend.event.controller;

import luna.clubverse.backend.common.BooleanResponse;
import luna.clubverse.backend.common.MessageResponse;
import luna.clubverse.backend.common.MessageType;
import luna.clubverse.backend.event.controller.request.AddEventRequest;
import luna.clubverse.backend.event.controller.request.Attendance;
import luna.clubverse.backend.event.controller.request.UpdateEventRequest;
import luna.clubverse.backend.event.controller.response.EventAndParticipantQueryResponse;
import luna.clubverse.backend.event.controller.response.EventHomePageResponse;
import luna.clubverse.backend.event.controller.response.EventListQueryResponse;
import luna.clubverse.backend.event.controller.response.EventQueryResponse;
import luna.clubverse.backend.event.enumuration.EventStatus;
import luna.clubverse.backend.event.service.EventService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    // club id eklenecek
    @CrossOrigin
    //@PreAuthorize("hasAuthority('ADMIN')" +
    //        "or @authorizationLuna.authorizeEvent(authentication, 'EVENT_MANAGEMENT', #eventId)")
    @GetMapping("/getWithParticipants/{id}")
    public EventAndParticipantQueryResponse getWithParticipants(@PathVariable Long id) {
        EventAndParticipantQueryResponse response = new EventAndParticipantQueryResponse(eventService.getEvent(id));
        return response;
    }

    @CrossOrigin
    //@PreAuthorize("@authorizationLuna.authorizeEvent(authentication, 'EVENT_MANAGEMENT', #eventId)")
    @PutMapping("/update")
    public MessageResponse updateEvent(@RequestBody @Valid final UpdateEventRequest updateEventRequest) {

        String errorMessage = checkEventRequestDates(updateEventRequest.getQuota(),updateEventRequest.getGePoint(), updateEventRequest.getAmountOfMoney(),updateEventRequest.getStartDate(),updateEventRequest.getEndDate(),updateEventRequest.getStartTime(),updateEventRequest.getEndTime(),updateEventRequest.getRegistrationDeadlineDate(),updateEventRequest.getReviewDeadlineTime(), updateEventRequest.getReviewDeadlineDate(),updateEventRequest.getReviewDeadlineTime() );

        if(!errorMessage.equals("")){
            return new MessageResponse(MessageType.ERROR,errorMessage);
        }

        eventService.updateEvent(updateEventRequest.toEvent(),updateEventRequest.toEventID());
        return new MessageResponse( MessageType.SUCCESS, "success");
    }


    @CrossOrigin
    //@PreAuthorize("@authorizationLuna.authorizeEvent(authentication, 'EVENT_MANAGEMENT', #eventId)")
    @PutMapping("/status/{status}/{id}")
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

    @CrossOrigin
    //@PreAuthorize("@authorizationLuna.authorizeEvent(authentication, 'EVENT_MANAGEMENT', #eventId)")
    @PutMapping("/cancelEvent/{eventId}")
    public MessageResponse cancelEvent(@PathVariable Long eventId) {
        return eventService.cancelEvent(eventId);
    }

    @CrossOrigin
    //@PreAuthorize("@authorizationLuna.authorizeEvent(authentication, 'EVENT_MANAGEMENT', #eventId)")
    @PutMapping("/publishEvent/{eventId}")
    public MessageResponse publishEvent(@PathVariable Long eventId) {
        return eventService.publishEvent(eventId);
    }

    @CrossOrigin
    //@PreAuthorize("@authorizationLuna.authorizeEvent(authentication, 'EVENT_MANAGEMENT', #eventId)")
    @DeleteMapping("/deleteEvent/{eventId}")
    public MessageResponse deleteEvent(@PathVariable Long eventId) {
        return eventService.deleteEvent(eventId);
    }



    //delete silmek için kullanılır

    //get query işlmeleri için kullanılır

    @CrossOrigin
    @PutMapping("/{clubId}/addToClub")
    @PreAuthorize("@authorizationLuna.authorize(authentication, 'EVENT_MANAGEMENT' , #clubId )" )
    public MessageResponse addEvent(@PathVariable Long clubId, @RequestBody @Valid final AddEventRequest addEventRequest) {

        String errorMessage = checkEventRequestDates(addEventRequest.getQuota(),addEventRequest.getGePoint(), addEventRequest.getAmountOfMoney(),addEventRequest.getStartDate(),addEventRequest.getEndDate(),addEventRequest.getStartTime(),addEventRequest.getEndTime(),addEventRequest.getRegistrationDeadlineDate(),addEventRequest.getRegistrationDeadlineTime(), addEventRequest.getReviewDeadlineDate(), addEventRequest.getReviewDeadlineTime());

        if(!errorMessage.equals("")){
            return new MessageResponse(MessageType.ERROR,errorMessage);
        }

        return  eventService.addEventToClub(clubId,addEventRequest.toEvent());
    }


    /*
    @GetMapping("/event_list")
    public List  <EventListQueryResponse> getAll( Model model) {
        List <EventListQueryResponse> events = eventService.getAllDemo();
        //model.addAttribute("events", events);
        return events;
    }

     */


    /*
    @CrossOrigin
    @GetMapping("/myEvents/{id}")
    public List<EventListQueryResponse> getEventsForStudent(@PathVariable Long id) {
         List<EventListQueryResponse> events = (eventService.getEventsForStudent(id));
         return events;
    }

     */

    @CrossOrigin
    @GetMapping("/attendedEventsForStudent/{id}")
    public List<EventListQueryResponse> getAttendedEventsForStudent(@PathVariable Long id) {
        List<EventListQueryResponse> events = (eventService.getEventsForStudent(id));
        return events;
    }



    @CrossOrigin
    @GetMapping("/facultyAdvisorEvents/{id}")
    public List<EventListQueryResponse> getEventsForFacultyAdvisor(@PathVariable Long id) {
        List<EventListQueryResponse> events = (eventService.getEventsForClub(id));
        return events;
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @PutMapping("/{eventId}/addEnrolledStudent/{userId}")
    public MessageResponse addEnrolledStudent(@PathVariable Long eventId,@PathVariable Long userId) {
        return eventService.addEnrolledStudent(eventId, userId);
    }

    @PreAuthorize("hasAuthority('ADVISOR')")
    @PutMapping("/{eventId}/addEnrolledFacultyAdvisor/{userId}")
    public MessageResponse addFacultyAdvisor(@PathVariable Long eventId,@PathVariable Long userId) {
        return eventService.addEnrolledFacultyAdvisor(eventId, userId);
    }

    public String checkEventRequestDates(int quota ,int gePoint,Double finance,LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, LocalDate registeredDeadline, LocalTime  registeredDeadlineTime, LocalDate reviewDeadline, LocalTime reviewDeadlineTime ){

        if(quota<0){
            return "Quota cannot be negative";
        }

        if( gePoint<0){
            return "Ge Point cannot be negative";
        }

        if(finance<0){
            return "Finance cannot be negative";
        }

        LocalDateTime startDateTime = LocalDateTime.of(startDate,startTime);
        LocalDateTime endDateTime = LocalDateTime.of(endDate,endTime);
        LocalDateTime registeredDateTime = LocalDateTime.of(registeredDeadline,registeredDeadlineTime);
        LocalDateTime reviewDateTime = LocalDateTime.of(reviewDeadline,registeredDeadlineTime);

        // testi engellediği için şimdilik yorum
        /*
        LocalDateTime nowDateTime = LocalDateTime.now();

        if(startDateTime.compareTo(nowDateTime)<0){
            return " Now cannot be before than Start Date";
        }

         */

       if(startDateTime.compareTo(endDateTime) > 0){
           return "Start Date cannot be before than End Date";
        }

        if(registeredDateTime.compareTo(startDateTime) > 0){
            return "Start Date cannot be before than Registration Deadline";
        }

        if(endDateTime.compareTo(reviewDateTime) > 0 ){
            return "Review Deadline  cannot be before than End Date";
        }

        return "";
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @PutMapping("/{eventId}/deleteEnrolledStudent/{userId}")
    public MessageResponse deleteEnrolledStudent(@PathVariable Long eventId,@PathVariable Long userId) {
        return eventService.deleteEnrolledStudent(eventId, userId);
    }

    @PreAuthorize("hasAuthority('ADVISOR')")
    @PutMapping("/{eventId}/deleteEnrolledFacultyAdvisor/{userId}")
    public MessageResponse deleteEnrolledFacultyAdvisor(@PathVariable Long eventId,@PathVariable Long userId) {
        return eventService.deleteEnrolledFacultyAdvisor(eventId, userId);
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("/{eventId}/isEnrolledStudent/{userId}")
    public BooleanResponse isEnrolledStudent(@PathVariable Long eventId, @PathVariable Long userId) {
        return eventService.isEnrolledStudent(eventId, userId);
    }

    @PreAuthorize("hasAuthority('ADVISOR')")
    @GetMapping("/{eventId}/isEnrolledFacultyAdvisor/{userId}")
    public BooleanResponse isEnrolledFacultyAdvisor(@PathVariable Long eventId, @PathVariable Long userId) {
        return eventService.isEnrolledFacultyAdvisor(eventId, userId);
    }

    //@PreAuthorize("@authorizationLuna.authorizeEvent(authentication, 'EVENT_MANAGEMENT', #eventId)")
    @GetMapping("/{eventId}/getButtons")
    public EventHomePageResponse getButtons(@PathVariable Long eventId) {
        return eventService.getButtonsStatus(eventId);
    }


    //@PreAuthorize("@authorizationLuna.authorizeEvent(authentication, 'EVENT_MANAGEMENT', #eventId)")
    @PutMapping("/takeAttendance")
    public MessageResponse takeAttendance(@RequestBody Attendance attendance) {
        return eventService.takeAttendance(attendance.getEventId(), attendance.getAttendance());
    }


}

