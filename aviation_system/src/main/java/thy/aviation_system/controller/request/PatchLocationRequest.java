package thy.aviation_system.controller.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatchLocationRequest {
    @NotNull(message = "Id is required")
    private Long id;

    private String name;

    @Size(max = 100)
    private String country;

    @Size(max = 100)
    private String city;

    @Size(min = 3, max = 10)
    @Pattern(regexp = "^[A-Z0-9]+$", message = "Only uppercase letters and numbers")
    private String locationCode;
}
