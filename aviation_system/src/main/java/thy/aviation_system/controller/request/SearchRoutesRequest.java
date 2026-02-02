package thy.aviation_system.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class SearchRoutesRequest {
    @NotBlank(message = "Origin location code is required")
    private String originCode;

    @NotBlank(message = "Destination location code is required")
    private String destinationCode;

    private LocalDate date;
}
