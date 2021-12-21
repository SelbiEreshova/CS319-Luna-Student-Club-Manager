package luna.clubverse.backend.user.controller;

import luna.clubverse.backend.club.controller.response.ClubQueryResponse;
import luna.clubverse.backend.event.controller.response.EventListQueryResponse;
import luna.clubverse.backend.event.controller.response.EventQueryResponse;
import luna.clubverse.backend.user.entity.Student;
import luna.clubverse.backend.user.service.CustomUserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class CustomUserRestController {

    private final CustomUserService customUserService;

    public CustomUserRestController(CustomUserService customUserService) {
        this.customUserService = customUserService;
    }

    @GetMapping("/studentHomePage/{userId}")
    public List<EventListQueryResponse> getFutureEventsOfStudent(@PathVariable Long userId) {
        return customUserService.getFutureEventsOfStudent(userId);
    }

    @CrossOrigin
    @GetMapping("/getClubsOfStudent/{studentId}")
    public List<ClubQueryResponse> getClubsOfUser(@PathVariable Long studentId ) {
        return customUserService.getClubsOfStudent(studentId);
    }
/*
    @CrossOrigin
    @GetMapping("/studentIsMember/{studentId}/{clubId}")
    public Boolean studentIsMember(@PathVariable Long studentId, @PathVariable Long clubId ) {
        return customUserService.studentIsMember(studentId,clubId);
    }

 */


}
