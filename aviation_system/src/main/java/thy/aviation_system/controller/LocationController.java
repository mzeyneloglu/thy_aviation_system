package thy.aviation_system.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import thy.aviation_system.constants.ApiEndpoints;
import thy.aviation_system.constants.ApiGroups;
import thy.aviation_system.controller.request.InsertLocationRequest;
import thy.aviation_system.controller.request.UpdateLocationRequest;
import thy.aviation_system.controller.response.BatchInsertLocationResponse;
import thy.aviation_system.dto.LocationDTO;
import thy.aviation_system.service.LocationService;
import java.util.List;

@RestController
@Tag(name = ApiGroups.LOCATION_API)
@RequestMapping(ApiEndpoints.LOCATION_API_URL)
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @PostMapping("/get-location-by-with-id")
    @ResponseStatus(HttpStatus.OK)
    public LocationDTO getLocationWithBydId(@RequestParam Long id) {
        return locationService.getLocationWithBydId(id);
    }

    @GetMapping("/get-all-locations")
    @ResponseStatus(HttpStatus.OK)
    public List<LocationDTO> getAllLocations() {
        return locationService.getAllLocations();
    }

    @PostMapping("/get-location-by-with-location-code")
    @ResponseStatus(HttpStatus.OK)
    public LocationDTO getLocationByLocationCode(@RequestParam String locationCode) {
        return locationService.getLocationByLocationCode(locationCode);
    }

    @PostMapping("/insert-location")
    @ResponseStatus(HttpStatus.OK)
    public LocationDTO insertLocation(@RequestBody @Valid InsertLocationRequest insertLocationRequest) {
        return locationService.insertLocation(insertLocationRequest);
    }

    @PostMapping("/batch-insert-locations")
    @ResponseStatus(HttpStatus.OK)
    public BatchInsertLocationResponse batchInsertLocations(@RequestBody @Valid List<InsertLocationRequest> insertLocationRequests) {
        return locationService.batchInsertLocations(insertLocationRequests);
    }

    @PutMapping("/update-location")
    @ResponseStatus(HttpStatus.OK)
    public LocationDTO updateLocationWithById(@RequestBody @Valid UpdateLocationRequest updateLocationRequest) {
        return locationService.updateLocationWithById(updateLocationRequest);
    }

    @DeleteMapping("/delete-location")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocationWithById(@RequestParam Long id) {
        locationService.deleteLocationWithById(id);
    }

}
