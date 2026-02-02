package thy.aviation_system.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import thy.aviation_system.constants.ApiEndpoints;
import thy.aviation_system.constants.ApiGroups;
import thy.aviation_system.controller.request.*;
import thy.aviation_system.controller.response.BatchInsertTransportationResponse;
import thy.aviation_system.dto.TransportationDTO;
import thy.aviation_system.service.TransportationService;
import java.util.List;

@RestController
@Tag(name = ApiGroups.TRANSPORTATION_API)
@RequestMapping(ApiEndpoints.TRANSPORTATION_API_URL)
@RequiredArgsConstructor
public class TransportationController {

    private final TransportationService transportationService;

    @PostMapping("/get-transportation-by-with-id")
    @ResponseStatus(HttpStatus.OK)
    public TransportationDTO getTransportationWithBydId(@RequestParam Long id) {
        return transportationService.getTransportationWithBydId(id);
    }

    @GetMapping("/get-all-transportations")
    @ResponseStatus(HttpStatus.OK)
    public List<TransportationDTO> getAllTransportations() {
        return transportationService.getAllTransportations();
    }

    @PostMapping("/insert-transportation")
    @ResponseStatus(HttpStatus.OK)
    public TransportationDTO insertTransportation(@RequestBody @Valid InsertTransportationRequest insertTransportationRequest) {
        return transportationService.insertTransportation(insertTransportationRequest);
    }

    @PostMapping("/batch-insert-transportations")
    @ResponseStatus(HttpStatus.OK)
    public BatchInsertTransportationResponse batchInsertLocations(@RequestBody @Valid List<InsertTransportationRequest> insertTransportationRequests) {
        return transportationService.batchInsertLocations(insertTransportationRequests);
    }

    @PutMapping("/update-transportation")
    @ResponseStatus(HttpStatus.OK)
    public TransportationDTO updateTransportationWithById(@RequestBody @Valid UpdateTransportationRequest updateTransportationRequest) {
        return transportationService.updateTransportationWithById(updateTransportationRequest);
    }

    @PatchMapping("/patch-transportation")
    @ResponseStatus(HttpStatus.OK)
    public TransportationDTO patchTransportationWithById(@RequestBody @Valid PatchTransportationRequest patchTransportationRequest) {
        return transportationService.patchTransportationWithById(patchTransportationRequest);
    }

    @DeleteMapping("/delete-transportation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransportationWithById(@RequestParam Long id) {
        transportationService.deleteTransportationWithById(id);
    }



}
