package luna.clubverse.backend.user.entity;

import lombok.Getter;
import lombok.Setter;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.user.enums.UserType;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@DiscriminatorValue("faculty_advisor")
public class FacultyAdvisor extends User{

    @ManyToMany(mappedBy = "enrolledFacultyAdvisors")
    private Set<Event> enrolledEvents;

    @ManyToMany(mappedBy = "attendedFacultyAdvisors")
    private Set<Event> attendedEvents;

    @OneToOne
    @JoinColumn(name = "club_id", referencedColumnName = "id")
    private Club club;

    public FacultyAdvisor(Long id, String username, String password, String name, String surname, byte[] profilePhoto, @Email String mail, Set<Authority> authorities) {
        super(id, username, password, name,surname,profilePhoto, UserType.FACULTY_ADVISOR, mail, authorities);
        enrolledEvents = new HashSet<Event>();
        attendedEvents = new HashSet<Event>();
    }

    public FacultyAdvisor() {
    }
}

