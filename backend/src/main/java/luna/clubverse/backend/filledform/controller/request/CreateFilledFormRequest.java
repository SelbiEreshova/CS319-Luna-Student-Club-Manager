package luna.clubverse.backend.filledform.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import luna.clubverse.backend.filledform.entity.FilledForm;

import java.util.List;
@RequiredArgsConstructor // her türlü constract oluşturur yazmaya gerek yok
@Getter
@ToString
public class CreateFilledFormRequest {

    private List<String> answers;
    //private Long ClubId;

    public FilledForm toFilledForm( )
    {
        return new FilledForm(answers);
    }
}


