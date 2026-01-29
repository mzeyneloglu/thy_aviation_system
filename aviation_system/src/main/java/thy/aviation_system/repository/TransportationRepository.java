package thy.aviation_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thy.aviation_system.entity.Transportation;

public interface TransportationRepository extends JpaRepository<Transportation, Long> {
}
