package thy.aviation_system.controller.response;

import lombok.*;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoutesResponse {
    private String origin;

    private String destination;

    private List<RouteSegmentResponse> segments;

    private int totalSegments;

    private String description;
}
