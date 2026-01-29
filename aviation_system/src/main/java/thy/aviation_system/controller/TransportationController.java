package thy.aviation_system.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thy.aviation_system.constants.ApiEndpoints;
import thy.aviation_system.constants.ApiGroups;

@RestController
@Tag(name = ApiGroups.TRANSPORTATION_API)
@RequestMapping(ApiEndpoints.TRANSPORTATION_API_URL)
public class TransportationController {
}
