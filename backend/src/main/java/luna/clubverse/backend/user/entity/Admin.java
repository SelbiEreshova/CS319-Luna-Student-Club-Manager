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
@DiscriminatorValue("admin")
public class Admin extends User{

    public Admin(Long id, String username, String password, String name, String surname,byte[] profilePhoto, @Email String mail, Set<Authority> authorities) {
        super(id, username, password, name, surname,profilePhoto, UserType.ADMIN, mail, authorities);
    }

    public Admin() {

    }
}
