package luna.clubverse.backend.emptyform.entity;

import lombok.Getter;
import lombok.Setter;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "form")
public class EmptyForm extends BaseEntity{

      //  private Long c
        protected EmptyForm()
        {

        }
        public EmptyForm(List<String> questions)
        {
            this.questions = questions;
        }
        @ElementCollection
        private List<String> questions;

        @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinColumn(name = "club_id")
        private Club club;

        public void addQuestion( String question )
        {
            questions.add(question);
        }

    public void deleteQuestion( int questionIndex )
    {
        questions.remove(questionIndex);
    }

    public void updateForm(EmptyForm emptyForm)
    {
        this.questions = emptyForm.questions;
        //System.out.println(emptyForm.questions);
    }
}



