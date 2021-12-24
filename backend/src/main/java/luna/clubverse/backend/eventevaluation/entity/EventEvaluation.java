package luna.clubverse.backend.eventevaluation.entity;

import lombok.Getter;
import lombok.Setter;
import luna.clubverse.backend.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "event_evaluation")
public class EventEvaluation extends BaseEntity {

    int point;

    long eventId;

    long userId;


    public EventEvaluation( int point , long eventId, long userId )
    {
        this.point = point;
        this.eventId = eventId;
        this.userId = userId;
    }

    public EventEvaluation() {

    }
}
