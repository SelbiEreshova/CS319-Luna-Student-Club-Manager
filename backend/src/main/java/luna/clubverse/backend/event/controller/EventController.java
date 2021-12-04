package luna.clubverse.backend.event.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventController {


    @RequestMapping("/event_list")
    public String welcome() {
        return "event_list";
    }
}
