package luna.clubverse.backend.eventevaluation.repository;

import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.eventevaluation.entity.EventEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventEvaluationRepository extends JpaRepository<EventEvaluation, Long> {
}
