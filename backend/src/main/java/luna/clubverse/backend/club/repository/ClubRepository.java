package luna.clubverse.backend.club.repository;

import luna.clubverse.backend.club.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {
}
