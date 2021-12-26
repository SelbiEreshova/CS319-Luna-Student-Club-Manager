package luna.clubverse.backend.emptyform.repository;

import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.emptyform.entity.EmptyForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmptyFormRepository extends JpaRepository<EmptyForm, Long> {
Optional<EmptyForm> findByClub(Club club);
}
