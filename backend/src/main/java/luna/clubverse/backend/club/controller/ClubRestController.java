package luna.clubverse.backend.club.controller;


import luna.clubverse.backend.club.controller.request.AddClubRequest;
import luna.clubverse.backend.club.controller.request.UpdateClubRequest;
import luna.clubverse.backend.club.controller.request.UploadPhotoRequest;
import luna.clubverse.backend.club.controller.response.*;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.club.service.ClubService;
import luna.clubverse.backend.common.MessageResponse;
import luna.clubverse.backend.common.MessageType;
import luna.clubverse.backend.event.controller.response.EventListQueryResponse;
import luna.clubverse.backend.user.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/club")
public class ClubRestController {

    private final ClubService clubService;
    private final AuthenticationService authenticationService;

    public ClubRestController(ClubService clubService, AuthenticationService authenticationService) { this.clubService = clubService;
        this.authenticationService = authenticationService;
    }

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
    @DeleteMapping("/delete/{clubId}")
    public MessageResponse deleteClub(@PathVariable Long clubId) {
        return clubService.deleteClub(clubId);
    }

    @CrossOrigin
    @GetMapping("/get/{id}")
    public ClubQueryResponse getClub(@PathVariable Long id) {
        return new ClubQueryResponse(clubService.getClub(id));
    }

    @CrossOrigin
    @GetMapping("/getWithPermissions/{clubId}/{studentId}")
    public ClubManagerClubQueryResponse getClubWithPermissions(@PathVariable Long clubId, @PathVariable Long studentId) {

        return clubService.getClubWithPermissions(clubId,studentId);
    }

    @CrossOrigin
    @GetMapping("/getEvents/{id}")
    public List<EventListQueryResponse> getClubEvents(@PathVariable Long id) {
        return clubService.getEventsOfClub(id);
    }

    @CrossOrigin
    @PutMapping("/{clubId}/applyToClub/{studentId}")
    public String applyToClub(@PathVariable Long clubId,@PathVariable Long studentId ) {
        clubService.applyToClub(clubId,studentId);
        return "success";
    }

    @CrossOrigin
    @PutMapping("/{clubId}/directApplicationToClub/{studentId}")
    public String directApplicationToClub(@PathVariable Long clubId,@PathVariable Long studentId ) {
        clubService.directApplicationToClub(clubId,studentId);
        return "success";
    }

    @CrossOrigin
    @PutMapping("/{clubId}/approveAppliedStudent/{studentId}")
    public String approveAppliedStudent(@PathVariable Long clubId,@PathVariable Long studentId ) {
        clubService.removeFromAppliedStudent(clubId,studentId, true);
        return "success";
    }

    @CrossOrigin
    @PutMapping("/{clubId}/disapproveAppliedStudent/{studentId}")
    public String disapproveFromAppliedStudent(@PathVariable Long clubId,@PathVariable Long studentId ) {
        clubService.removeFromAppliedStudent(clubId,studentId, false);
        return "success";
    }

    //Message Response için dön************************************
    @CrossOrigin
    @PutMapping("/create-club")
    public MessageResponse createClub(@Valid @RequestBody AddClubRequest request) {
        Club club = clubService.addClub(request.toClub() );
        MessageResponse responseFromAccount = authenticationService.createClubDirectorAccount(request.toClubDirector(), club);
        authenticationService.sendClubRequestToAdvisor(request.getAdvisorMail());
        authenticationService.createFacultyAdvisorAccount(request.toFacultyAdvisor(), club);
        return responseFromAccount;
        //return new MessageResponse(MessageType.SUCCESS, "Club is created successfully");
    }


    @CrossOrigin
    @GetMapping("/getAllClubs")
    public List<ClubQueryResponse> getAllClubs( ) {
        return clubService.getAllClub();
    }

    @CrossOrigin
    @GetMapping("/get_club_list")
    public List<ClubListQueryResponse> getClubList(){
       return clubService.getClubList();
    }

    @CrossOrigin
    @PutMapping("/photo")
    public MessageResponse uploadPhoto(@Valid @RequestBody UploadPhotoRequest request) {
        System.out.println( request.getFile() );
        return new MessageResponse(MessageType.SUCCESS,"success");
    }

    @CrossOrigin
    @GetMapping("/get_club_director_members/{clubId}")
    public List<MemberQueryresponse> getMemberList(@PathVariable Long clubId){
        return clubService.getMembers(clubId);
    }


    @CrossOrigin
    @GetMapping("/finance_table/{clubId}")
    public FinanceTableResponse getFinanceTable(@PathVariable Long clubId){
        return new FinanceTableResponse(clubService.getClub(clubId).getFinanceTable());
    }


}
