package luna.clubverse.backend.form.controller.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import luna.clubverse.backend.form.entity.Form;

import java.util.List;

@RequiredArgsConstructor // her türlü constract oluşturur yazmaya gerek yok
@Getter
@ToString
public class CreateFormRequest {

    private List<String> questions;


    public Form toForm( )
    {
        return new Form(questions);
    }
}
