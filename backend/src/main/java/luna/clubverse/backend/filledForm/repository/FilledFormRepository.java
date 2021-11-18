package luna.clubverse.backend.filledForm.repository;

import luna.clubverse.backend.filledForm.entity.FilledForm;
import luna.clubverse.backend.form.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilledFormRepository extends JpaRepository<FilledForm, Long> {
}
