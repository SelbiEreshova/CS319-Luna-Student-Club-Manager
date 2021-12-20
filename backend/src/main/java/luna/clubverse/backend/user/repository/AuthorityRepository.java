package luna.clubverse.backend.user.repository;

import luna.clubverse.backend.user.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {

    Optional<Authority> findByAuthority(String authority);
}
