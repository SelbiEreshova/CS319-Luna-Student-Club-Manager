package luna.clubverse.backend.event.repository;

import luna.clubverse.backend.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {


   // @Query(name = "SELECT * FROM public.event ORDER BY id ASC ", nativeQuery=true)
//    List<Event> findAllEvents();


    @Query(value = "SELECT * FROM public.event WHERE club_id = :id", nativeQuery = true)
    List<Event> findNameById(@Param("id") Long id);


    @Query(value = "SELECT * FROM public.event WHERE  public.event.id = ( SELECT public.event_enrolled_student.event_id FROM public.event_enrolled_student WHERE public.event_enrolled_student.enrolled_student_id = :studentId )"  , nativeQuery = true)
    List<Event> findEventsByStudentId(@Param("studentId") Long studentId);
}



