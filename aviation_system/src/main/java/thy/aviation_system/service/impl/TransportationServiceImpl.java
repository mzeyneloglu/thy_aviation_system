package thy.aviation_system.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import thy.aviation_system.constants.AviationSystemValidationRules;
import thy.aviation_system.controller.request.InsertTransportationRequest;
import thy.aviation_system.controller.request.UpdateTransportationRequest;
import thy.aviation_system.controller.response.BatchErrorDetail;
import thy.aviation_system.controller.response.BatchInsertTransportationResponse;
import thy.aviation_system.dto.TransportationDTO;
import thy.aviation_system.entity.Location;
import thy.aviation_system.entity.Transportation;
import thy.aviation_system.exception.BusinessValidationException;
import thy.aviation_system.mapper.TransportationMapper;
import thy.aviation_system.repository.LocationRepository;
import thy.aviation_system.repository.TransportationRepository;
import thy.aviation_system.service.TransportationService;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransportationServiceImpl implements TransportationService {

    Logger logger = LoggerFactory.getLogger(TransportationServiceImpl.class);
    private final TransportationRepository transportationRepository;
    private final LocationRepository locationRepository;
    private final TransportationMapper transportationMapper;

    @Override
    public TransportationDTO getTransportationWithBydId(Long id) {
        Transportation transportation = transportationRepository.getTransportationById(id)
                .orElseThrow(()-> new BusinessValidationException(AviationSystemValidationRules.TRANSPORTATION_NOT_FOUND));
        return transportationMapper.toDTO(transportation);
    }

    @Override
    public List<TransportationDTO> getAllTransportations() {
        return transportationMapper.toDTOList(transportationRepository.findAll());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public TransportationDTO insertTransportation(InsertTransportationRequest insertTransportationRequest) {
        Location originLocation = locationRepository.getLocationById(insertTransportationRequest.getOriginLocationId())
                .orElseThrow(() -> new BusinessValidationException(AviationSystemValidationRules.LOCATION_NOT_FOUND));

        Location destinationLocation = locationRepository.getLocationById(insertTransportationRequest.getDestinationLocationId())
                .orElseThrow(() -> new BusinessValidationException(AviationSystemValidationRules.LOCATION_NOT_FOUND));

        if (transportationRepository.existsByOriginLocationAndDestinationLocationAndTransportationTypeAndOperatingDays(originLocation,
                destinationLocation, insertTransportationRequest.getTransportationType(), insertTransportationRequest.getOperatingDays()))
            throw new BusinessValidationException(AviationSystemValidationRules.INSERTED_TRANSPORTATION);

        if (destinationLocation.getLocationCode().equals(originLocation.getLocationCode())){
            throw new BusinessValidationException(AviationSystemValidationRules.ORIGIN_LOCATION_AND_DESTINATION_LOCATION_CANNOT_BE_SAME);
        }

        Transportation transportation = transportationMapper.toEntity(insertTransportationRequest);
        transportation.setOriginLocation(originLocation);
        transportation.setDestinationLocation(destinationLocation);
        transportationRepository.save(transportation);
        return transportationMapper.toDTO(transportation);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public BatchInsertTransportationResponse batchInsertLocations(List<InsertTransportationRequest> insertTransportationRequests) {
        List<TransportationDTO> successList = new ArrayList<>();
        List<BatchErrorDetail> errorList = new ArrayList<>();

        for (int i = 0; i < insertTransportationRequests.size(); i++) {
            try {
                Location originLocation = locationRepository.getLocationById(insertTransportationRequests.get(i).getOriginLocationId())
                        .orElseThrow(() -> new BusinessValidationException(AviationSystemValidationRules.LOCATION_NOT_FOUND));

                Location destinationLocation = locationRepository.getLocationById(insertTransportationRequests.get(i).getDestinationLocationId())
                        .orElseThrow(() -> new BusinessValidationException(AviationSystemValidationRules.LOCATION_NOT_FOUND));

                if (destinationLocation.getLocationCode().equals(originLocation.getLocationCode())){
                    throw new BusinessValidationException(AviationSystemValidationRules.ORIGIN_LOCATION_AND_DESTINATION_LOCATION_CANNOT_BE_SAME);
                }

                Transportation transportation = transportationMapper.toEntity(insertTransportationRequests.get(i));
                transportation.setOriginLocation(originLocation);
                transportation.setDestinationLocation(destinationLocation);
                transportationRepository.save(transportation);
                successList.add(transportationMapper.toDTO(transportation));
            } catch (Exception e) {
                errorList.add(BatchErrorDetail.builder()
                        .index(i)
                        .errorMessage(e.getMessage())
                        .build());
            }
        }
        return BatchInsertTransportationResponse.builder()
                .totalRequested(insertTransportationRequests.size())
                .totalSuccess(successList.size())
                .totalFailed(errorList.size())
                .successList(successList)
                .errorList(errorList)
                .build();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public TransportationDTO updateTransportationWithById(UpdateTransportationRequest updateTransportationRequest) {
        Location originLocation = locationRepository.getLocationById(updateTransportationRequest.getOriginLocationId())
                .orElseThrow(() -> new BusinessValidationException(AviationSystemValidationRules.LOCATION_NOT_FOUND));

        Location destinationLocation = locationRepository.getLocationById(updateTransportationRequest.getDestinationLocationId())
                .orElseThrow(() -> new BusinessValidationException(AviationSystemValidationRules.LOCATION_NOT_FOUND));

        Transportation transportation = transportationRepository.getTransportationById(updateTransportationRequest.getId())
                .orElseThrow(()-> new BusinessValidationException(AviationSystemValidationRules.TRANSPORTATION_NOT_FOUND));

        transportationMapper.updateEntity(updateTransportationRequest, transportation);
        transportation.setOriginLocation(originLocation);
        transportation.setDestinationLocation(destinationLocation);
        transportationRepository.save(transportation);
        return transportationMapper.toDTO(transportation);
    }

    @Override
    public void deleteTransportationWithById(Long id) {
        Transportation transportation = transportationRepository.getTransportationById(id)
                .orElseThrow(()-> new BusinessValidationException(AviationSystemValidationRules.TRANSPORTATION_NOT_FOUND));
        transportationRepository.delete(transportation);
    }

}
