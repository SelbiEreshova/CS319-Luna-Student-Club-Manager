package luna.clubverse.backend.emptyform.repository;

import luna.clubverse.backend.emptyform.entity.EmptyForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmptyFormRepository extends JpaRepository<EmptyForm, Long> {
}
