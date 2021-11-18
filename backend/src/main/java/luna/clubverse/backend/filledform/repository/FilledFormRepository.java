package luna.clubverse.backend.filledform.repository;

import luna.clubverse.backend.filledform.entity.FilledForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilledFormRepository extends JpaRepository<FilledForm, Long> {
}
