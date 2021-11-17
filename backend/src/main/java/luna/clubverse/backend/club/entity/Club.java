package luna.clubverse.backend.club.entity;


import lombok.Getter;
import luna.clubverse.backend.common.entity.BaseEntity;
import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.financetable.entity.FinanceTable;

import javax.persistence.*;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "club")
    private Set<Event> events;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "finance_table_id")
    private FinanceTable financeTable;

    // ekli değil
    //private EmptyForm applicationForm = null

    protected Club() {
    }

    public Club(String name, String logo, String description, FinanceTable financeTable) {
        this.name = name;
        this.logo = logo;
        this.description = description;
        this.financeTable = financeTable;
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
