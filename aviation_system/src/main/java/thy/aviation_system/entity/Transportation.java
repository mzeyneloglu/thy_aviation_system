package thy.aviation_system.entity;

import jakarta.persistence.*;
import lombok.*;
import thy.aviation_system.constants.TransportationType;

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
}