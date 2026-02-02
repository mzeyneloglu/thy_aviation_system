package thy.aviation_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thy.aviation_system.entity.Location;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<Location> getLocationById(Long id);

    List<Location> findAllByOrderById();

    Optional<Location> getLocationByLocationCode(String locationCode);
}
