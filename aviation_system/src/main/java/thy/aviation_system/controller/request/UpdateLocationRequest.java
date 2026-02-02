package thy.aviation_system.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateLocationRequest {
    @NotNull(message = "Id is required")
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 255)
    private String name;

    @NotBlank(message = "Country is required")
    @Size(max = 100)
    private String country;

    @NotBlank(message = "City is required")
    @Size(max = 100)
    private String city;

    @NotBlank(message = "Location code is required")
    @Size(min = 3, max = 10)
    @Pattern(regexp = "^[A-Z0-9]+$", message = "Only uppercase letters and numbers")
    private String locationCode;
}
