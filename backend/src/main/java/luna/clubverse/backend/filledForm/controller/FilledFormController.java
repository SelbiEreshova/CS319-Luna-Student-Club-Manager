package luna.clubverse.backend.filledForm.controller;


import luna.clubverse.backend.event.controller.response.EventQueryResponse;
import luna.clubverse.backend.filledForm.controller.request.CreateFilledFormRequest;
import luna.clubverse.backend.filledForm.controller.response.FilledFormQueryResponse;
import luna.clubverse.backend.filledForm.entity.FilledForm;
import luna.clubverse.backend.filledForm.service.FilledFormService;
import luna.clubverse.backend.form.controller.request.CreateFormRequest;
import luna.clubverse.backend.form.service.FormService;
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
        filledFormService.createFilledForm(clubId,studentId,createFilledFormRequest.toFilledForm());
        return "success "; // return type will be changed, except from get requests, there will be same type of response
    }


    @CrossOrigin
    @GetMapping("/getFilledForm/{id}")
    public FilledFormQueryResponse getFilledForm(@PathVariable Long id) {
        return new FilledFormQueryResponse(filledFormService.getFilledForm(id));
    }
}
