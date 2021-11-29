package luna.clubverse.backend.event.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventController {

    @RequestMapping("/Create_eventd")
    public String createEventPage() {
        return "Create_event";
    }
}
