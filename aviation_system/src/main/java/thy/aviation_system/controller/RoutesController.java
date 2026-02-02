package thy.aviation_system.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import thy.aviation_system.constants.ApiEndpoints;
import thy.aviation_system.constants.ApiGroups;
import thy.aviation_system.controller.request.SearchRoutesRequest;
import thy.aviation_system.controller.response.RouteSearchResponse;
import thy.aviation_system.service.RoutesService;

@RestController
@Tag(name = ApiGroups.ROUTES_API)
@RequestMapping(ApiEndpoints.ROUTES_API_URL)
@RequiredArgsConstructor
public class RoutesController {

    private final RoutesService routesService;

    @PostMapping("/search-routes")
    @ResponseStatus(HttpStatus.OK)
    public RouteSearchResponse searchRoutes(@RequestBody @Valid SearchRoutesRequest request) {
        return routesService.searchRoutes(request);
    }

}
