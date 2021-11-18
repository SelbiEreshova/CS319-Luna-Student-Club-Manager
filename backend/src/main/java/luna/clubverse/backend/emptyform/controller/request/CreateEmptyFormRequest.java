package luna.clubverse.backend.emptyform.controller.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import luna.clubverse.backend.emptyform.entity.EmptyForm;

import java.util.List;

@RequiredArgsConstructor // her türlü constract oluşturur yazmaya gerek yok
@Getter
@ToString
public class CreateEmptyFormRequest {

    private List<String> questions;


    public EmptyForm toForm( )
    {
        return new EmptyForm(questions);
    }
}
