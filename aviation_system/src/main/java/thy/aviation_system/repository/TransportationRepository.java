package thy.aviation_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import thy.aviation_system.constants.TransportationType;
import thy.aviation_system.entity.Location;
import thy.aviation_system.entity.Transportation;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransportationRepository extends JpaRepository<Transportation, Long> {
    Optional<Transportation> getTransportationById(Long id);

    List<Transportation> findAll();

    List<Transportation> findByTransportationType(TransportationType transportationType);

    List<Transportation> findByOriginLocationIdAndDestinationLocationId(Long fromLocationId, Long toLocationId);

    boolean existsByOriginLocationAndDestinationLocationAndTransportationTypeAndOperatingDays(Location originLocation, Location destinationLocation, TransportationType transportationType, String operatingDays);
}
