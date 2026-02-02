package thy.aviation_system.entity;

import jakarta.persistence.*;
import lombok.*;
import thy.aviation_system.constants.TransportationType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "transportations", indexes = {
        @Index(name = "idx_transportation_origin", columnList = "origin_location_id"),
        @Index(name = "idx_transportation_destination", columnList = "destination_location_id"),
        @Index(name = "idx_transportation_type", columnList = "transportation_type")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transportation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_location_id", nullable = false)
    private Location originLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_location_id", nullable = false)
    private Location destinationLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "transportation_type", nullable = false, length = 20)
    private TransportationType transportationType;

    @Column(name = "operating_days", length = 50)
    private String operatingDays;

    @Transient
    public List<Integer> getOperatingDaysAsList() {
        if (operatingDays == null || operatingDays.trim().isEmpty()) {
            return List.of();
        }
        return Arrays.stream(operatingDays.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    @Transient
    public boolean operatesOnDay(int dayOfWeek) {
        if (operatingDays == null || operatingDays.trim().isEmpty()) {
            return true;
        }
        return getOperatingDaysAsList().contains(dayOfWeek);
    }
}