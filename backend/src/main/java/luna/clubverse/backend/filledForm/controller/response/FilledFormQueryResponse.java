package luna.clubverse.backend.filledForm.controller.response;

import lombok.Getter;
import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.event.enumuration.EventStatus;
import luna.clubverse.backend.filledForm.entity.FilledForm;

import java.time.LocalDate;
import java.util.List;
@Getter
public class FilledFormQueryResponse {

    private final List<String> answers;

    public FilledFormQueryResponse(FilledForm filledForm) {
        this.answers = filledForm.getAnswers();
    }
}

