package luna.clubverse.backend.filledform.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import luna.clubverse.backend.filledform.entity.FilledForm;
import luna.clubverse.backend.filledform.enums.FormStatus;

import java.util.List;
@RequiredArgsConstructor // her türlü constract oluşturur yazmaya gerek yok
@Getter
@ToString
public class CreateFilledFormRequest {

    private List<String> answers;
    //private Long ClubId;

    public FilledForm toFilledForm( )
    {
        FilledForm filledForm = new FilledForm(answers);
        filledForm.setStatus(FormStatus.PENDING);

        return filledForm;
    }
}


