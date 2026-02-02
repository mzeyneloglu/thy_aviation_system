package thy.aviation_system.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RouteSearchResponse {
    private String origin;
    private String destination;
    private LocalDate searchDate;
    private int totalRoutes;
    private List<RoutesResponse> routes;
}
