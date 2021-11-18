package luna.clubverse.backend.emptyform.controller;

import luna.clubverse.backend.filledform.controller.response.EmptyFormQueryResponse;
import luna.clubverse.backend.emptyform.controller.request.CreateEmptyFormRequest;
import luna.clubverse.backend.emptyform.controller.request.UpdateEmptyFormRequest;
import luna.clubverse.backend.emptyform.service.EmptyFormService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RequestMapping("/form")
@RestController
public class EmptyFormController
{

    private final EmptyFormService emptyFormService;


    public EmptyFormController(EmptyFormService emptyFormService) {
        this.emptyFormService = emptyFormService;
    }


    @CrossOrigin
    @PostMapping("/{clubId}/createFormToClub")
    public String createForm(@PathVariable Long clubId, @RequestBody @Valid final CreateEmptyFormRequest createEmptyFormRequest) {
        emptyFormService.createFormToClub(clubId, createEmptyFormRequest.toForm());
        return "success "; // return type will be changed, except from get requests, there will be same type of response
    }

    @CrossOrigin
    @GetMapping("/getForm/{id}")
    public EmptyFormQueryResponse getEmptyForm(@PathVariable Long id) {
        return new EmptyFormQueryResponse(emptyFormService.getForm(id));
    }


    @CrossOrigin
    @PutMapping("/update/{id}") // post yeni şey eklemek için yapılır
    public String updateForm(@PathVariable Long id, @RequestBody @Valid final UpdateEmptyFormRequest updateEmptyFormRequest) {
        emptyFormService.updateForm(id, updateEmptyFormRequest.toForm());
        return "success";
    }
}
