package luna.clubverse.backend.event.repository;

import luna.clubverse.backend.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
