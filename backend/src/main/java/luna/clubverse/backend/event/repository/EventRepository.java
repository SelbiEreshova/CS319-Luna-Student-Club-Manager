package luna.clubverse.backend.event.repository;

import luna.clubverse.backend.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {


   // @Query(name = "SELECT * FROM public.event ORDER BY id ASC ", nativeQuery=true)
//    List<Event> findAllEvents();
}
