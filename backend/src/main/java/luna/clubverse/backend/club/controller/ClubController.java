package luna.clubverse.backend.club.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClubController {

    @RequestMapping("/app/club_home_page_{userType}/{clubId}")
    public String getClubHomePage( Model model,@PathVariable String userType, @PathVariable Long clubId) {
        final String url = "club_home_page_";
        model.addAttribute("clubId",clubId);
        return url + userType;
    }

    @RequestMapping("/app/open_all_clubs")
    public String openAllClubs() {
          return "all_club_list";
    }

    @RequestMapping("/app/open_clubs_student")
    public String openStudentClubs() {
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

    @RequestMapping("/app/finance_table_manager/{clubId}")
    public String openFinanceTableForManager(Model model, @PathVariable Long clubId) {
        model.addAttribute("clubId", clubId);
        return "finance_table_manager";
    }

    @RequestMapping("/app/clubdirector_applications")
    public String openClubDirector() {
        return "clubdirector_applications";
    }

    @RequestMapping("app/view_application/{clubId}/{userId}")
    public String viewApplication(Model model, @PathVariable Long clubId,  @PathVariable Long userId ) {
        model.addAttribute("clubId",clubId);
        model.addAttribute("userId", userId);
        return "clubdirector_view_application";
    }

    @RequestMapping("/app/student_applications")
    public String openMyApplication() {
        return "student_my_applications";
    }

    @RequestMapping("app/view_application_student/{clubId}/{userId}")
    public String viewApplicationStudent(Model model, @PathVariable Long clubId,  @PathVariable Long userId ) {
        model.addAttribute("clubId",clubId);
        model.addAttribute("userId", userId);
        return "student_view_application";
    }

    @RequestMapping("/app/manager_applications/{clubId}")
    public String openManagerApplication(Model model, @PathVariable Long clubId) {
        model.addAttribute("clubId",clubId);
        return "manager_applications";
    }
}
