package luna.clubverse.backend.form.controller;

import luna.clubverse.backend.event.controller.request.AddEventRequest;
import luna.clubverse.backend.event.service.EventService;
import luna.clubverse.backend.form.controller.request.CreateFormRequest;
import luna.clubverse.backend.form.entity.Form;
import luna.clubverse.backend.form.service.FormService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RequestMapping("/form")
@RestController
public class FormController
{

    private final FormService formService;


    public FormController(FormService formService) {
        this.formService = formService;
    }


    @CrossOrigin
    @PostMapping("/{clubId}/createFormToClub")
    public String createForm(@PathVariable Long clubId, @RequestBody @Valid final CreateFormRequest createFormRequest) {
        formService.createFormToClub(clubId,createFormRequest.toForm());
        return "success "; // return type will be changed, except from get requests, there will be same type of response
    }
}
