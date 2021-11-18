package luna.clubverse.backend.filledform.controller.response;

import lombok.Getter;
import luna.clubverse.backend.emptyform.entity.EmptyForm;

import java.util.List;
@Getter
public class EmptyFormQueryResponse {

    private final List<String> questions;

    public EmptyFormQueryResponse(EmptyForm emptyForm) {
        this.questions = emptyForm.getQuestions();
    }


}

