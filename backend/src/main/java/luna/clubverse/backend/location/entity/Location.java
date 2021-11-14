package luna.clubverse.backend.location.entity;

import lombok.Getter;
import lombok.experimental.Accessors;
import luna.clubverse.backend.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Accessors(fluent = true)
@Table(name = "location")
public class Location extends BaseEntity {
    private boolean isInBilkent;

    private String building;

    private String description;

    private String classroom;

}
