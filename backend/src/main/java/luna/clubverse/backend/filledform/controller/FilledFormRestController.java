package luna.clubverse.backend.filledform.controller;


import luna.clubverse.backend.common.MessageResponse;
import luna.clubverse.backend.filledform.controller.request.CreateFilledFormRequest;
import luna.clubverse.backend.filledform.controller.response.FilledFormQueryResponse;
import luna.clubverse.backend.filledform.service.FilledFormService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RequestMapping("/filledForm")
@RestController
public class FilledFormRestController {

    FilledFormService filledFormService;

    public FilledFormRestController(FilledFormService filledFormService) {
        this.filledFormService = filledFormService;
    }

    @CrossOrigin
    @PostMapping("/{clubId}/createFilledForm/{studentId}")
    public MessageResponse createForm(@PathVariable Long clubId, @PathVariable Long studentId,
                                      @RequestBody @Valid final CreateFilledFormRequest createFilledFormRequest) {
        return  filledFormService.createFilledForm(clubId,studentId,createFilledFormRequest.toFilledForm());
    }


    @CrossOrigin
    @GetMapping("/getFilledForm/{id}")
    public FilledFormQueryResponse getFilledForm(@PathVariable Long id) {
        return new FilledFormQueryResponse(filledFormService.getFilledForm(id));
    }
}
