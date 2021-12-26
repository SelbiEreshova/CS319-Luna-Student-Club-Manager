package luna.clubverse.backend.filledform.entity;

import lombok.Getter;
import lombok.Setter;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.common.entity.BaseEntity;
import luna.clubverse.backend.emptyform.entity.EmptyForm;
import luna.clubverse.backend.filledform.enums.FormStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "filled_form")
public class FilledForm extends BaseEntity {

    @ElementCollection
    private List <String> answers;

    private Long studentId;


    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "club_id")
    private Club club;

    @ElementCollection
    private List<String> questionsOfFilled;

    @JoinColumn(name = "status")
    private FormStatus status;

    protected FilledForm()
    {
    }
    public FilledForm( List<String> answers)
    {
        this.answers = answers;
    }


    public void addQuestionAndAnswers( String answer)
    {
        answers.add(answer);
    }

    public void deleteQuestionAndAnswers( int answerIndex )
    {
        answers.remove(answerIndex);
    }
}
