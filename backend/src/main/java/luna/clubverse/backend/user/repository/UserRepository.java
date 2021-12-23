package luna.clubverse.backend.user.repository;

import luna.clubverse.backend.user.entity.Student;
import luna.clubverse.backend.user.entity.User;
import luna.clubverse.backend.user.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String  username);

    boolean existsByMail(String mail);

    List<User> findAllByUsertypeIs(UserType userType);


}
