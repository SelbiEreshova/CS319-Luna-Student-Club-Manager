package luna.clubverse.backend.eventevaluation.repository;

import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.eventevaluation.entity.EventEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventEvaluationRepository extends JpaRepository<EventEvaluation, Long> {
    @Query(value = "SELECT * FROM public.event_evaluation WHERE event_id = :eventId AND user_id = :userId ", nativeQuery = true)
    EventEvaluation findByIdandEventId(@Param("userId") Long userId, @Param("eventId") Long eventId );
}
