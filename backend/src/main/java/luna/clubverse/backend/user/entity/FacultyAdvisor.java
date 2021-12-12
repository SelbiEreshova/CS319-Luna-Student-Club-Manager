package luna.clubverse.backend.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luna.clubverse.backend.user.enums.UserType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@Getter
@Setter
@DiscriminatorValue("faculty_advisor")
public class FacultyAdvisor extends User{
    public FacultyAdvisor(Long id, String username, String password, String name, @Email String mail, Set<Authority> authorities) {
        super(id, username, password, name, UserType.FACULTY_ADVISOR, mail, authorities);
    }

    public FacultyAdvisor() {
    }
}

