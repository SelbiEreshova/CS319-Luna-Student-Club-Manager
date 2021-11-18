package luna.clubverse.backend.filledForm.controller.response;

import lombok.Getter;
import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.event.enumuration.EventStatus;
import luna.clubverse.backend.filledForm.entity.FilledForm;
import luna.clubverse.backend.form.entity.Form;

import java.time.LocalDate;
import java.util.List;
@Getter
public class FormQueryResponse {

    private final List<String> questions;

    public FormQueryResponse(Form form) {
        this.questions = form.getQuestions();
    }


}

