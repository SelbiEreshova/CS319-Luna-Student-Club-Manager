package luna.clubverse.backend.filledform.controller;


import luna.clubverse.backend.filledform.controller.request.CreateFilledFormRequest;
import luna.clubverse.backend.filledform.controller.response.FilledFormQueryResponse;
import luna.clubverse.backend.filledform.service.FilledFormService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RequestMapping("/filledForm")
@RestController
public class FilledFormController {

    FilledFormService filledFormService;

    public FilledFormController(FilledFormService filledFormService) {
        this.filledFormService = filledFormService;
    }

    @CrossOrigin
    @PostMapping("/{clubId}/{studentId}/createFilledForm")
    public String createForm(@PathVariable Long clubId, @PathVariable Long studentId,
                             @RequestBody @Valid final CreateFilledFormRequest createFilledFormRequest) {
        System.out.println(studentId + "adaaaaaaaaaaaaaad");
        filledFormService.createFilledForm(clubId,studentId,createFilledFormRequest.toFilledForm());
        return "success "; // return type will be changed, except from get requests, there will be same type of response
    }


    @CrossOrigin
    @GetMapping("/getFilledForm/{id}")
    public FilledFormQueryResponse getFilledForm(@PathVariable Long id) {
        return new FilledFormQueryResponse(filledFormService.getFilledForm(id));
    }
}
