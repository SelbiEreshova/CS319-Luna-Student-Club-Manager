package luna.clubverse.backend.form.controller;

import luna.clubverse.backend.event.controller.request.AddEventRequest;
import luna.clubverse.backend.event.service.EventService;
import luna.clubverse.backend.filledForm.controller.response.FilledFormQueryResponse;
import luna.clubverse.backend.filledForm.controller.response.FormQueryResponse;
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

    @CrossOrigin
    @GetMapping("/getForm/{id}")
    public FormQueryResponse getFilledForm(@PathVariable Long id) {
        return new FormQueryResponse(formService.getForm(id));
    }
}
