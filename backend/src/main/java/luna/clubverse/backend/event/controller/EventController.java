package luna.clubverse.backend.event.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/event")
public class EventController {

    @RequestMapping("/create")
    public String createEventPage() {
        return "Create_event";
    }
}
