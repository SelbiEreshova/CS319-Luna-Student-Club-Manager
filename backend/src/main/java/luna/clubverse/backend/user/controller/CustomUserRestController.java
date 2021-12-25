package luna.clubverse.backend.user.controller;

import luna.clubverse.backend.club.controller.request.UploadPhotoRequest;
import luna.clubverse.backend.club.controller.response.ClubManagerCheckQueryResponse;
import luna.clubverse.backend.common.MessageResponse;
import luna.clubverse.backend.event.controller.response.EventListQueryResponse;
import luna.clubverse.backend.user.controller.response.ClubDirectorQueryResponse;
import luna.clubverse.backend.user.controller.response.FacultyAdvisorQueryResponse;
import luna.clubverse.backend.user.controller.response.StudentQueryResponse;
import luna.clubverse.backend.user.entity.User;
import luna.clubverse.backend.user.service.CustomUserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomUserRestController {

    private final CustomUserService customUserService;

    public CustomUserRestController(CustomUserService customUserService) {
        this.customUserService = customUserService;
    }

    //GET MAPPING KOTU
    @GetMapping("/studentHomePage/{userId}")
    public List<EventListQueryResponse> getFutureEventsOfStudent(@PathVariable Long userId) {
        return customUserService.getFutureEventsOfStudent(userId);
    }

    @GetMapping("/getPastEventsOfStudent/{userId}")
    public List<EventListQueryResponse> getPastEventsOfStudent(@PathVariable Long userId) {
        return customUserService.getPastEventsOfStudent(userId);
    }

    @GetMapping("/getOnGoingEventsOfStudent/{userId}")
    public List<EventListQueryResponse> getOnGoingEventsOfStudent(@PathVariable Long userId) {
        return customUserService.getOnGoingEventsOfStudent(userId);
    }



    @GetMapping("/getFutureEventsOfFA/{userId}")
    public List<EventListQueryResponse> getFutureEventsOfFA(@PathVariable Long userId) {
        return customUserService.getFutureEventsOfFA(userId);
    }

    @GetMapping("/getPastEventsOfFA/{userId}")
    public List<EventListQueryResponse> getPastEventsOfFA(@PathVariable Long userId) {
        return customUserService.getPastEventsOfFA(userId);
    }

    @GetMapping("/getOnGoingEventsOfFA/{userId}")
    public List<EventListQueryResponse> getOnGoingEventsOfFA(@PathVariable Long userId) {
        return customUserService.getOnGoingEventsOfFA(userId);
    }

    @CrossOrigin
    @GetMapping("/getClubsOfStudent/{studentId}")
    public List<ClubManagerCheckQueryResponse> getClubsOfUser(@PathVariable Long studentId ) {
        return customUserService.getClubsOfStudent(studentId);
    }

    @CrossOrigin
    @GetMapping("/getProfileOfStudent/{studentId}")
    public StudentQueryResponse getProfileOfUser(@PathVariable Long studentId ) {

        return new StudentQueryResponse(customUserService.getStudent(studentId));
    }

    @CrossOrigin
    @GetMapping("/getFacultyAdvisor/{id}")
    public FacultyAdvisorQueryResponse getProfileOfFacultyAdvisor(@PathVariable Long id ) {

        return new FacultyAdvisorQueryResponse(customUserService.getFacultyAdvisor(id));
    }


    @CrossOrigin
    @GetMapping("/getClubDirector/{id}")
    public ClubDirectorQueryResponse getProfileOfClubDirector(@PathVariable Long id ) {

        return new ClubDirectorQueryResponse(customUserService.getClubDirector(id));
    }

    @CrossOrigin
    @GetMapping("/getAllStudents")
    public List<StudentQueryResponse> getStudents() {
        return customUserService.getAllStudents();
    }

    @CrossOrigin
    @PutMapping("/uploadPhotoForUser/{userId}")
    public MessageResponse uploadPhotoForClubBackground(@PathVariable Long userId, @Valid @RequestBody UploadPhotoRequest request) {
        return customUserService.changeProfileImage(userId, request.getFile());
    }
}
