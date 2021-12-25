package luna.clubverse.backend.emptyform.controller.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import luna.clubverse.backend.emptyform.entity.EmptyForm;

import java.util.List;

@RequiredArgsConstructor // her türlü constract oluşturur yazmaya gerek yok
@Getter
@ToString
public class SetEmptyFormRequest {

    private List<String> questions;

    private Long clubId;
    public SetEmptyFormRequest( Long clubId, List<String> questions)
    {
        this.clubId = clubId;
        this.questions = questions;
    }
    public EmptyForm toForm( )
    {
        return new EmptyForm(questions);
    }
}
