package luna.clubverse.backend.club.controller;


import luna.clubverse.backend.club.controller.request.AddClubRequest;
import luna.clubverse.backend.club.controller.request.UpdateClubRequest;
import luna.clubverse.backend.club.controller.response.ClubQueryResponse;
import luna.clubverse.backend.club.service.ClubService;
import luna.clubverse.backend.event.controller.response.EventQueryResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/club")
public class ClubController {

    private final ClubService clubService;

    public ClubController(ClubService clubService) { this.clubService = clubService;}

    @CrossOrigin
    @PostMapping("/add")
    public String addClub(@RequestBody @Valid final AddClubRequest addClubRequest) {
        clubService.addClub(addClubRequest.toClub());
        return "success"; // return type will be changed, except from get requests, there will be same type of response
    }

    @CrossOrigin
    @PutMapping("/update/{id}")
    public String updateClub(@PathVariable Long id, @RequestBody @Valid final UpdateClubRequest updateClubRequest) {
        clubService.updateClub(id,updateClubRequest.toClub());
        return "success"; // return type will be changed, except from get requests, there will be same type of response
    }

    @CrossOrigin
    @GetMapping("/get/{id}")
    public ClubQueryResponse getClub(@PathVariable Long id) {
        return new ClubQueryResponse(clubService.getClub(id));
    }

    @CrossOrigin
    @GetMapping("/getEvents/{id}")
    public List<EventQueryResponse> getClubEvents(@PathVariable Long id) {
        return clubService.getEventsOfClub(id);
    }
}
