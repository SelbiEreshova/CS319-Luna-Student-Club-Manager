package luna.clubverse.backend.club.entity;


import lombok.Getter;
import luna.clubverse.backend.common.entity.BaseEntity;
import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.financetable.entity.FinanceTable;
import luna.clubverse.backend.user.entity.ClubDirector;
import luna.clubverse.backend.user.entity.FacultyAdvisor;
import luna.clubverse.backend.user.entity.Student;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Table(name = "club")
public class Club extends BaseEntity {

    private String name;

    private String logo;

    private String description;

    @OneToOne(mappedBy = "club")
    @JoinColumn(name = "club_director_id")
    private ClubDirector clubDirector;

    @OneToOne(mappedBy = "club")
    //@JoinColumn(name = "faculty_advisor_id")
    private FacultyAdvisor facultyAdvisor;


    @ManyToMany
    @JoinTable(name = "club_member",
            joinColumns = @JoinColumn(name = "club_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private Set<Student> members;

    @ManyToMany
    @JoinTable(name = "club_applied_student",
            joinColumns = @JoinColumn(name = "club_id"),
            inverseJoinColumns = @JoinColumn(name = "applied_student_id")
    )
    private Set<Student> appliedStudents;

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

    public void addMembers(Student student){
        members.add(student);
    }


    public void addAppliedStudents(Student student){
        appliedStudents.add(student);
    }

    public boolean removeAppliedStudents(Student student){
        return appliedStudents.remove(student);
    }

}
