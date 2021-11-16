package luna.clubverse.backend.location.entity;

import luna.clubverse.backend.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;


//@Getter
//@Accessors(fluent = true)
@Entity
@Table(name = "location")
public class Location extends BaseEntity {


    private String building;

    private boolean isInBilkent;

    private String description;

    private String classroom;

    //one to one falan yazmadım

    protected Location() {
    }

    public Location( String building, String description, String classroom,boolean isInBilkent) {
        this.isInBilkent = isInBilkent;
        this.building = building;
        this.description = description;
        this.classroom = classroom;
    }
}
