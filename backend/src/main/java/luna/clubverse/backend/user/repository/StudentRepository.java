package luna.clubverse.backend.user.repository;

import luna.clubverse.backend.user.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}