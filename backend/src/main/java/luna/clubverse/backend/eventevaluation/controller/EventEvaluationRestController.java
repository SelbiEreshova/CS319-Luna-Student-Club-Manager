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
    @PostMapping("/add/{event_id}/{student_id}")
    public MessageResponse addEventEvaluation(@Valid @RequestBody  EventEvaluation eventEvaluation, @PathVariable Long event_id, @PathVariable Long student_id) {
         return eventEvaluationService.addEventEvaluationForStudent(eventEvaluation,event_id,student_id);
    }


}
