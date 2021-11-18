package luna.clubverse.backend.form.repository;

import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.form.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form, Long> {
}
