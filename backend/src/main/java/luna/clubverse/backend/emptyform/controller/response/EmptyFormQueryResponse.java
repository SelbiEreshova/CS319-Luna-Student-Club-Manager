package luna.clubverse.backend.emptyform.controller.response;

import lombok.Getter;
import luna.clubverse.backend.emptyform.entity.EmptyForm;

import java.util.List;
@Getter
public class EmptyFormQueryResponse {

    private final List<String> questions;
    private final Long clubID;

    public EmptyFormQueryResponse(EmptyForm emptyForm) {
        this.questions = emptyForm.getQuestions();
        this.clubID = emptyForm.getClub().id();
    }


}

