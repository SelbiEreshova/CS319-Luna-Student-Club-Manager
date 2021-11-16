package luna.clubverse.backend.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authority;

    private Long clubId;

    public Long getClubId() {
        return clubId;
    }

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;

    public Authority(String authority, Long clubId) {
        this.authority = authority;
        this.clubId = clubId;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Authority object = (Authority) obj;
        return (this.authority.equals(object.authority) && this.clubId == object.getClubId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(authority, clubId);
    }

    @Override
    public String toString() {
        return ("Authority: " + authority + " ClubID: " + clubId);
    }
}
