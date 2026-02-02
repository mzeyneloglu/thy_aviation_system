package thy.aviation_system.service;

import thy.aviation_system.controller.request.InsertTransportationRequest;
import thy.aviation_system.controller.request.PatchTransportationRequest;
import thy.aviation_system.controller.request.UpdateTransportationRequest;
import thy.aviation_system.controller.response.BatchInsertTransportationResponse;
import thy.aviation_system.dto.TransportationDTO;
import java.util.List;

public interface TransportationService {
    TransportationDTO getTransportationWithBydId(Long id);

    List<TransportationDTO> getAllTransportations();

    TransportationDTO insertTransportation(InsertTransportationRequest insertTransportationRequest);

    BatchInsertTransportationResponse batchInsertLocations(List<InsertTransportationRequest> insertTransportationRequests);

    TransportationDTO updateTransportationWithById(UpdateTransportationRequest updateTransportationRequest);

    TransportationDTO patchTransportationWithById(PatchTransportationRequest patchTransportationRequest);

    void deleteTransportationWithById(Long id);
}
