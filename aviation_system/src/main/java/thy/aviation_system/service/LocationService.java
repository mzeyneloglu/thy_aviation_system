package thy.aviation_system.service;

import thy.aviation_system.controller.request.InsertLocationRequest;
import thy.aviation_system.controller.request.UpdateLocationRequest;
import thy.aviation_system.controller.response.BatchInsertLocationResponse;
import thy.aviation_system.dto.LocationDTO;
import java.util.List;

public interface LocationService {
    LocationDTO getLocationWithBydId(Long id);

    List<LocationDTO> getAllLocations();

    LocationDTO getLocationByLocationCode(String locationCode);

    LocationDTO insertLocation(InsertLocationRequest insertLocationRequest);

    BatchInsertLocationResponse batchInsertLocations(List<InsertLocationRequest> insertLocationRequests);

    LocationDTO updateLocationWithById(UpdateLocationRequest updateLocationRequest);

    void deleteLocationWithById(Long id);

}
