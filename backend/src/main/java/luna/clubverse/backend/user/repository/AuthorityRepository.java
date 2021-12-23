package luna.clubverse.backend.user.repository;

import luna.clubverse.backend.user.entity.Authority;
import luna.clubverse.backend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {

    Optional<Authority> findByAuthority(String authority);

    List<Authority> findAllByUsersAndClubId(User user, Long clubId);
}
