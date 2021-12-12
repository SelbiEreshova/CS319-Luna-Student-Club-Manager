package luna.clubverse.backend.event.controller;

import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.club.service.ClubService;
import luna.clubverse.backend.event.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventController {

    private final EventService eventService;
    private final ClubService clubService;

    public EventController(EventService eventService, ClubService clubService) {
        this.eventService = eventService;
        this.clubService = clubService;
    }

    @RequestMapping("/app/open_create_event/{clubId}")
    public String getClubController(Model model, @PathVariable Long clubId) {
        //eventService.addEventToClub(clubId,);
        //model.addAttribute("club", club);
        Club club = clubService.getClub(clubId);
        return "create_event";
    }
}
