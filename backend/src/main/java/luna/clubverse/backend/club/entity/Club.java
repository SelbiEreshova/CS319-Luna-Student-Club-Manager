package luna.clubverse.backend.club.entity;


import lombok.Getter;
import luna.clubverse.backend.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "club")
public class Club extends BaseEntity {

    private String name;

    private String logo;

    private String description;

    // ekli değil
    // private ClubDirector clubDirector;

    // ekli değil
    //private FacultyAdvisor facultyAdvisor;

    // ekli değil
    //private Student[] members = null;

    // ekli değil
    //private Student[] appliedStudents = null;

    // private Event[] events = null;

    //private FinanceTable financeTable = null;

    // ekli değil
    //private EmptyForm applicationForm = null



    protected Club() {
    }

    public Club(String name, String logo, String description) {
        this.name = name;
        this.logo = logo;
        this.description = description;
    }

    public void update(Club club){
        this.name = club.name;
        this.logo = club.logo;
        this.description = club.description;

    }
}
