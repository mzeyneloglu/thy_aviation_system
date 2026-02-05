package thy.aviation_system.controller.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import thy.aviation_system.entity.TransportationType;

@Getter
@Setter
public class InsertTransportationRequest {

    @NotNull(message = "Origin location ID is required")
    private Long originLocationId;

    @NotNull(message = "Destination location ID is required")
    private Long destinationLocationId;

    @NotNull(message = "Transportation type is required")
    private TransportationType transportationType;

    @Pattern(regexp = "^([1-7](,[1-7])*)?$", message = "Operating days must be between 1-7")
    private String operatingDays;

}
