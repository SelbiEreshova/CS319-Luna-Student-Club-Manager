package luna.clubverse.backend.eventevaluation.controller;

import luna.clubverse.backend.event.controller.response.EventQueryResponse;
import luna.clubverse.backend.eventevaluation.entity.EventEvaluation;
import luna.clubverse.backend.eventevaluation.service.EventEvaluationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventEvaluation")
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
    @GetMapping("/add/")
    public void addEventEvaluation( EventEvaluation eventEvaluation) {
         eventEvaluationService.addEventEvaluation(eventEvaluation);
    }


}
