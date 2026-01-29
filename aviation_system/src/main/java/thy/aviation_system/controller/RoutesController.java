package thy.aviation_system.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thy.aviation_system.constants.ApiEndpoints;
import thy.aviation_system.constants.ApiGroups;

@RestController
@Tag(name = ApiGroups.ROUTES_API)
@RequestMapping(ApiEndpoints.ROUTES_API_URL)
public class RoutesController {
}
