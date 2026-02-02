package thy.aviation_system.controller.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import thy.aviation_system.constants.TransportationType;

@Getter
@Setter
public class PatchTransportationRequest {
    @NotNull(message = "Id is required")
    private Long id;

    private Long originLocationId;

    private Long destinationLocationId;

    private TransportationType transportationType;

    @Pattern(regexp = "^([1-7](,[1-7])*)?$", message = "Operating days must be between 1-7")
    private String operatingDays;
}
