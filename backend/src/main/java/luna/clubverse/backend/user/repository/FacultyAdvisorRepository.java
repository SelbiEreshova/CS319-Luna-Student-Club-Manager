package luna.clubverse.backend.user.repository;

import luna.clubverse.backend.user.entity.FacultyAdvisor;
import luna.clubverse.backend.user.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyAdvisorRepository extends JpaRepository<FacultyAdvisor, Long> {

    List<FacultyAdvisor> findAllByIdIsIn(List<Long> userIds);

}
