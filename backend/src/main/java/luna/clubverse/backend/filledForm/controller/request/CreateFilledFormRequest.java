package luna.clubverse.backend.filledForm.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import luna.clubverse.backend.filledForm.entity.FilledForm;
import luna.clubverse.backend.form.entity.Form;

import java.util.List;
@RequiredArgsConstructor // her türlü constract oluşturur yazmaya gerek yok
@Getter
@ToString
public class CreateFilledFormRequest {
    private List<String> answers;

    public FilledForm toFilledForm( )
    {
        return new FilledForm(answers);
    }
}


