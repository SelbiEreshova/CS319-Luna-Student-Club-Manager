package luna.clubverse.backend.location.entity;

import lombok.Getter;
import lombok.experimental.Accessors;
import luna.clubverse.backend.common.entity.BaseEntity;
import luna.clubverse.backend.event.entity.Event;

import javax.persistence.*;


@Getter
@Accessors(fluent = true)
@Entity
@Table(name = "location")
public class Location extends BaseEntity {


    private String building;

    private Boolean inBilkent;

    private String description;

    private String classroom;

    //one to one falan yazmadım

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "event_id")
    private Event event;

    protected Location() {
    }

    public Location( String building, String description, String classroom,boolean inBilkent) {
        this.inBilkent = inBilkent;
        this.building = building;
        this.description = description;
        this.classroom = classroom;
    }
}
