package luna.clubverse.backend.club.controller;

import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.club.service.ClubService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClubController {

    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @RequestMapping("/club_home_page_student/{clubId}")
    public String getClubController( Model model, @PathVariable Long clubId) {
        Club club = clubService.getClub(clubId);
        model.addAttribute("club", club);
        return "club_home_page_student";
    }
}
