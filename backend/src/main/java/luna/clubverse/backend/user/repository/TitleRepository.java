package luna.clubverse.backend.user.repository;

import luna.clubverse.backend.user.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<Title, Long> {
}
