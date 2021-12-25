package luna.clubverse.backend.filledform.repository;

import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.filledform.entity.FilledForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilledFormRepository extends JpaRepository<FilledForm, Long> {

    Optional<FilledForm> findByClubAndStudentId(Club club, Long id);
}
