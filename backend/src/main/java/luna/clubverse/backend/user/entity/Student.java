package luna.clubverse.backend.user.entity;

import lombok.Getter;
import lombok.Setter;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.user.enums.UserType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@Getter
@Setter
@DiscriminatorValue("student")
public class Student extends User{

    private int bilkentId;

    @ManyToMany(mappedBy = "appliedStudents")
    private Set<Club> waitingApprovalClubs;

    @ManyToMany(mappedBy = "members")
    private Set<Club> registeredClubs;

    @ManyToMany(mappedBy = "enrolledStudents")
    private Set<Event> enrolledEvents;

    @ManyToMany(mappedBy = "attendedStudents")
    private Set<Event> attendedEvents;

    public Student(Long id, String username, String password, String name, @Email String mail, Set<Authority> authorities, int bilkentId) {
        super(id, username, password, name, UserType.STUDENT, mail, authorities);
        this.bilkentId = bilkentId;
    }

    public Student() {

    }
}
