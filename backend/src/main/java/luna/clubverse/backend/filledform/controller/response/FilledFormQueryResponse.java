package luna.clubverse.backend.filledform.controller.response;

import lombok.Getter;
import luna.clubverse.backend.filledform.entity.FilledForm;

import java.util.List;
@Getter
public class FilledFormQueryResponse {

    private final List<String> answers;

    public FilledFormQueryResponse(FilledForm filledForm) {
        this.answers = filledForm.getAnswers();
    }
}

