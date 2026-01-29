package thy.aviation_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thy.aviation_system.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
