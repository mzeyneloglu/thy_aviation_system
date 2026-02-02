package thy.aviation_system.dto;

import lombok.*;
import thy.aviation_system.constants.TransportationType;
import thy.aviation_system.entity.Location;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransportationDTO {
    private Long id;
    private Location originLocation;
    private Location destinationLocation;
    private TransportationType transportationType;
    private String operatingDays;
}
