package luna.clubverse.backend.filledform.controller;


import luna.clubverse.backend.club.controller.response.ApplicationQueryResponse;
import luna.clubverse.backend.club.controller.response.MemberCandidateQueryResponse;
import luna.clubverse.backend.club.service.ClubService;
import luna.clubverse.backend.common.MessageResponse;
import luna.clubverse.backend.filledform.controller.request.CreateFilledFormRequest;
import luna.clubverse.backend.filledform.controller.response.FilledFormQueryResponse;
import luna.clubverse.backend.filledform.service.FilledFormService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/filledForm")
@RestController
public class FilledFormRestController {

    FilledFormService filledFormService;
     ClubService clubService;

    public FilledFormRestController(FilledFormService filledFormService, ClubService clubService) {
        this.filledFormService = filledFormService;
        this.clubService = clubService;
    }

    @CrossOrigin
    @PostMapping("/{clubId}/createFilledForm/{studentId}")
    public MessageResponse createForm(@PathVariable Long clubId, @PathVariable Long studentId,
                                      @RequestBody @Valid final CreateFilledFormRequest createFilledFormRequest) {
        clubService.applyToClub(clubId, studentId);
        return  filledFormService.createFilledForm(clubId,studentId,createFilledFormRequest.toFilledForm());
    }


    @CrossOrigin
    @GetMapping("/getFilledForm/{id}")
    public FilledFormQueryResponse getFilledForm(@PathVariable Long id) {
        return new FilledFormQueryResponse(filledFormService.getFilledForm(id));
    }

    @CrossOrigin
    @GetMapping("/getCandidateApplication/{clubId}/{userId}")
    public ApplicationQueryResponse getCandidate(@PathVariable Long clubId, @PathVariable Long userId) {
        return filledFormService.getCandidateApplication(clubId, userId);
    }
}
