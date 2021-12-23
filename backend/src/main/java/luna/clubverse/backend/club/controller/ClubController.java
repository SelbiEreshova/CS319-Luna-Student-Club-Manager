package luna.clubverse.backend.club.controller;

import luna.clubverse.backend.club.controller.response.ClubQueryResponse;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.club.service.ClubService;
import luna.clubverse.backend.financedata.entity.FinanceData;
import luna.clubverse.backend.financedata.service.FinanceDataService;
import luna.clubverse.backend.financetable.entity.FinanceTable;
import luna.clubverse.backend.user.entity.Student;
import luna.clubverse.backend.user.service.CustomUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ClubController {

    private final ClubService clubService;
    private final CustomUserService customUserService;

    public ClubController(ClubService clubService, CustomUserService customUserService) {
        this.clubService = clubService;
        this.customUserService = customUserService;
    }

    @RequestMapping("/app/club_home_page_{userType}/{clubId}/{userId}")
    public String getClubHomePage( Model model,@PathVariable String userType, @PathVariable Long clubId, @PathVariable Long userId) {

        final String url = "club_home_page_";
        /*

        boolean isMember = false;
        Club club = clubService.getClub(clubId);
        model.addAttribute("club", club);

        if(userType.equals("student")){
            isMember = customUserService.getStudent(userId).getRegisteredClubs().contains(club);
        }
        model.addAttribute("checkMember",isMember);

        //userType = userType.toLowerCase();

         */
        return url + userType;

    }

    @RequestMapping("/app/open_all_clubs")
    public String openAllClubs() {
          return "all_club_list";
    }

    @RequestMapping("/app/open_clubs_student/{userId}")
    public String openStudentClubs( @PathVariable Long userId) {
        return "club_list_student";
    }

    @RequestMapping("/app/club/{clubId}/director_members")
    public String clubDirectorMembers(Model model, @PathVariable Long clubId) {
        model.addAttribute("clubId", clubId);
        return "clubdirector_members_list";
    }

    @RequestMapping("/app/club_director_event_list")
    public String getClubDirectorEventList() {
        return "club_director_event_list";
    }

    @RequestMapping("/app/finance_table")
    public String openFinanceTable() {
        return "finance_table_director";
    }

    @RequestMapping("/app/finance_table_advisor")
    public String openFinanceTableForAdvisor() {
        return "finance_table_faculty_advisor";
    }




}
