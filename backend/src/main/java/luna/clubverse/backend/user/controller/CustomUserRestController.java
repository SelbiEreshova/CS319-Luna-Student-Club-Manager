package luna.clubverse.backend.user.controller;

import luna.clubverse.backend.event.controller.response.EventQueryResponse;
import luna.clubverse.backend.user.service.CustomUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomUserRestController {

    private final CustomUserService customUserService;

    public CustomUserRestController(CustomUserService customUserService) {
        this.customUserService = customUserService;
    }

    @GetMapping("/studentHomePage/{userId}")
    public List<EventQueryResponse> getFutureEventsOfStudent(@PathVariable Long userId) {
        return customUserService.getFutureEventsOfStudent(userId);
    }
}
