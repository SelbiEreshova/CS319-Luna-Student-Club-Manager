package luna.clubverse.backend.eventevaluation.controller;

import luna.clubverse.backend.common.MessageResponse;
import luna.clubverse.backend.event.controller.response.EventQueryResponse;
import luna.clubverse.backend.eventevaluation.entity.EventEvaluation;
import luna.clubverse.backend.eventevaluation.service.EventEvaluationService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/app/eventEvaluation")
public class EventEvaluationRestController {

    EventEvaluationService eventEvaluationService;

    EventEvaluationRestController( EventEvaluationService eventEvaluationService)
    {
        this.eventEvaluationService = eventEvaluationService;
    }

    @CrossOrigin
    @GetMapping("/get/{id}")
    public EventEvaluation getEventEvaluation(@PathVariable Long id) {
        return eventEvaluationService.getEventEvaluation(id);
    }

    @CrossOrigin
    @PostMapping("/add/{event_id}/student/{student_id}")
    public MessageResponse addEventEvaluationForStudent(@Valid @RequestBody  EventEvaluation eventEvaluation, @PathVariable Long event_id, @PathVariable Long student_id) {
         return eventEvaluationService.addEventEvaluationForStudent(eventEvaluation,event_id,student_id);
    }

    @CrossOrigin
    @PostMapping("/add/{event_id}/faculty_advisor/{faculty_advisor_id}")
    public MessageResponse addEventEvaluationForFacultyAdvisor(@Valid @RequestBody  EventEvaluation eventEvaluation, @PathVariable Long event_id, @PathVariable Long faculty_advisor_id) {
        return eventEvaluationService.addEventEvaluationForFacultyAdvisor(eventEvaluation,event_id,faculty_advisor_id);
    }

    @CrossOrigin
    @GetMapping("/get/{eventId}/{userId}")
    public EventEvaluation getEventEvaluationWithId(@PathVariable Long eventId, @PathVariable Long userId) {
        return eventEvaluationService.getEventEvaluationWithId( eventId,  userId);

    }



}
