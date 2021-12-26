package luna.clubverse.backend.club.controller.response;

import lombok.Getter;
import luna.clubverse.backend.emptyform.entity.EmptyForm;
import luna.clubverse.backend.filledform.entity.FilledForm;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ApplicationQueryResponse {

    private final String name_surname;
    private final String club_name;

    private final List<String> application;

    public ApplicationQueryResponse (String club_name,String name_surname, FilledForm filledForm) {
        this.club_name = club_name;
        this.name_surname = name_surname;
        application = new ArrayList<>();

        List<String> answers = filledForm.getAnswers();
        List<String> questions = filledForm.getQuestionsOfFilled();

        for(int i = 0; i < answers.size(); i++) {
            application.add(questions.get(i));
            application.add(answers.get(i));
        }

    }

}
