package luna.clubverse.backend.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@Getter
@Setter
@DiscriminatorValue("student")
public class Student extends User{

    private int bilkentId;

    //public Student(Long id, String username, String password, String name, @Email String mail, Set<Authority> authorities, int bilkentId) {
    //    super(id, username, password, name, mail, authorities);
    //    this.bilkentId = bilkentId;
    //}

    public Student() {

    }
}
