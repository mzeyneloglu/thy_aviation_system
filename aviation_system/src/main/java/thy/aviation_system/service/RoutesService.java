package thy.aviation_system.service;

import thy.aviation_system.controller.request.SearchRoutesRequest;
import thy.aviation_system.controller.response.RouteSearchResponse;

public interface RoutesService {
    RouteSearchResponse searchRoutes(SearchRoutesRequest request);
}
