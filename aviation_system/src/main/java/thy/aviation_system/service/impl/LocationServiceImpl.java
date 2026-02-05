package thy.aviation_system.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thy.aviation_system.constants.AviationSystemValidationRules;
import thy.aviation_system.controller.request.InsertLocationRequest;
import thy.aviation_system.controller.request.UpdateLocationRequest;
import thy.aviation_system.controller.response.BatchErrorDetail;
import thy.aviation_system.controller.response.BatchInsertLocationResponse;
import thy.aviation_system.dto.LocationDTO;
import thy.aviation_system.entity.Location;
import thy.aviation_system.exception.BusinessValidationException;
import thy.aviation_system.mapper.LocationMapper;
import thy.aviation_system.repository.LocationRepository;
import thy.aviation_system.service.LocationService;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    @Override
    public LocationDTO getLocationWithBydId(Long id) {
        Location location = locationRepository.getLocationById(id)
                .orElseThrow(() ->
                        new BusinessValidationException(
                                AviationSystemValidationRules.LOCATION_NOT_FOUND));

        return locationMapper.toDTO(location);
    }

    @Override
    public List<LocationDTO> getAllLocations() {
        return locationMapper.toDTOList(locationRepository.findAllByOrderById());
    }

    @Override
    public LocationDTO getLocationByLocationCode(String locationCode) {
        Location location = locationRepository.getLocationByLocationCode(locationCode)
                .orElseThrow(() -> new BusinessValidationException(AviationSystemValidationRules.LOCATION_NOT_FOUND));
        return locationMapper.toDTO(location);
    }

    @Override
    @Transactional
    public LocationDTO insertLocation(InsertLocationRequest insertLocationRequest) {
        Location insertedLocation = locationMapper.toEntity(insertLocationRequest);

        if (locationRepository.existsByLocationCode((insertedLocation.getLocationCode())))
            throw new BusinessValidationException(AviationSystemValidationRules.INSERTED_LOCATION);

        locationRepository.save(insertedLocation);
        return locationMapper.toDTO(insertedLocation);
    }

    @Override
    @Transactional
    public BatchInsertLocationResponse batchInsertLocations(List<InsertLocationRequest> insertLocationRequests) {
        List<LocationDTO> successList = new ArrayList<>();
        List<BatchErrorDetail> errorList = new ArrayList<>();

        for (int i = 0; i < insertLocationRequests.size(); i++) {
            try {
                InsertLocationRequest request = insertLocationRequests.get(i);
                Location entity = locationMapper.toEntity(request);

                if (locationRepository.existsByLocationCode((entity.getLocationCode())))
                    throw new BusinessValidationException(AviationSystemValidationRules.INSERTED_LOCATION);

                Location saved = locationRepository.save(entity);
                successList.add(locationMapper.toDTO(saved));
            } catch (Exception e) {
                errorList.add(BatchErrorDetail.builder()
                        .index(i)
                        .errorMessage(e.getMessage())
                        .build());
            }
        }
        return BatchInsertLocationResponse.builder()
                .totalRequested(insertLocationRequests.size())
                .totalSuccess(successList.size())
                .totalFailed(errorList.size())
                .successList(successList)
                .errorList(errorList)
                .build();
    }

    @Override
    @Transactional
    public LocationDTO updateLocationWithById(UpdateLocationRequest updateLocationRequest) {
        Location location = locationRepository.getLocationById(updateLocationRequest.getId())
                .orElseThrow(() -> new BusinessValidationException(AviationSystemValidationRules.LOCATION_NOT_FOUND));
        locationMapper.updateEntity(updateLocationRequest, location);
        locationRepository.save(location);
        return locationMapper.toDTO(location);
    }

    @Override
    @Transactional
    public void deleteLocationWithById(Long id) {
        Location location = locationRepository.getLocationById(id)
                .orElseThrow(() -> new BusinessValidationException(AviationSystemValidationRules.LOCATION_NOT_FOUND));
        locationRepository.delete(location);
    }
}
