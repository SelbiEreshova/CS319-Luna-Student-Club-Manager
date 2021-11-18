package luna.clubverse.backend.form.entity;

import lombok.Getter;
import lombok.Setter;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.common.entity.BaseEntity;
import luna.clubverse.backend.event.enumuration.EventStatus;
import luna.clubverse.backend.financedata.entity.FinanceData;
import luna.clubverse.backend.location.entity.Location;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "form")
public class Form extends BaseEntity{

      //  private Long c
        protected Form()
        {

        }
        public Form(List<String> questions)
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
}



