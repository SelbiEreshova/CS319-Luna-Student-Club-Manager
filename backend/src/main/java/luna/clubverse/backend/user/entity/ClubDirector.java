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
@DiscriminatorValue("club_director")
public class ClubDirector extends User{

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "club_id")
    private Club club;

    public ClubDirector(Long id, String username, String password, String name,String surname, @Email String mail, Set<Authority> authorities) {
        super(id, username, password, name,surname, UserType.CLUB_DIRECTOR, mail, authorities);
    }

    public ClubDirector() {

    }
}

