package luna.clubverse.backend.user.repository;

import luna.clubverse.backend.user.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByIdIsIn(List<Long> userIds);

    boolean existsByBilkentId(int bilkentId);
}