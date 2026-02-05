package thy.aviation_system.controller.response;

import lombok.*;
import thy.aviation_system.entity.TransportationType;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteSegmentResponse {
    private int order;
    private String originName;
    private String originCode;
    private String destinationName;
    private String destinationCode;
    private TransportationType transportationType;
    private String operatingDays;
    private String description;
}
