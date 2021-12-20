package luna.clubverse.backend.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.user.enums.UserType;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@Getter
@Setter
@DiscriminatorValue("faculty_advisor")
public class FacultyAdvisor extends User{


    @OneToOne
    @JoinColumn(name = "club_id", referencedColumnName = "id")
    private Club club;

    public FacultyAdvisor(Long id, String username, String password, String name, String surname, @Email String mail, Set<Authority> authorities) {
        super(id, username, password, name,surname, UserType.FACULTY_ADVISOR, mail, authorities);
    }

    public FacultyAdvisor() {
    }
}

