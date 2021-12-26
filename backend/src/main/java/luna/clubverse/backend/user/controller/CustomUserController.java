package luna.clubverse.backend.user.controller;

import luna.clubverse.backend.club.controller.response.MemberCandidateQueryResponse;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.club.service.ClubService;
import luna.clubverse.backend.common.MessageResponse;
import luna.clubverse.backend.event.controller.request.Attendance;
import luna.clubverse.backend.event.controller.response.EventListQueryResponse;
import luna.clubverse.backend.event.service.EventService;
import luna.clubverse.backend.user.controller.response.ApplicationListQueryResponse;
import luna.clubverse.backend.user.entity.Student;
import luna.clubverse.backend.user.service.CustomUserService;
import luna.clubverse.backend.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class CustomUserController {
    private CustomUserService customUserService;

    public CustomUserController(CustomUserService customUserService) {
        this.customUserService = customUserService;
    }
    @RequestMapping("/app/student_profile/{studentId}")
    public String getStudentProfile(@PathVariable Long studentId) {
        //eventService.addEventToClub(clubId,);
        //model.addAttribute("club", club);
        //Student student = customUserService.getStudent(studentId);
        return "student_profile";
    }

    @RequestMapping("/app/student_profile")
    public String getStudentProfile() {
        //eventService.addEventToClub(clubId,);
        //model.addAttribute("club", club);
        //Student student = customUserService.getStudent(studentId);
        return "student_profile";
    }


    @RequestMapping("/app/faculty_advisor_profile")
    public String getFacultyAdvisorProfile() {
        //eventService.addEventToClub(clubId,);
        //model.addAttribute("club", club);
        //Student student = customUserService.getStudent(studentId);
        return "faculty_profile";
    }

    @RequestMapping("/app/admin_student_list")
    public String getStudents() {
        return "admin_student_list";
    }


    @RequestMapping("/app/club_director_profile")
    public String getClubDirectorProfile() {
        //eventService.addEventToClub(clubId,);
        //model.addAttribute("club", club);
        //Student student = customUserService.getStudent(studentId);
        return "club_profile";
    }

    @RequestMapping("/app/about_us")
    public String openAboutUs() {
        return "about_us";
    }

    @RequestMapping("/app/admin_faculty_advisor_list")
    public String getFacultyAdvisors() {
        return "admin_faculty_advisor_list";
    }



}










