package luna.clubverse.backend.location.repository;

import luna.clubverse.backend.location.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
