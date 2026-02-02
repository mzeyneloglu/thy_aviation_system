package thy.aviation_system.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO {
    private Long id;
    private String name;
    private String country;
    private String city;
    private String locationCode;
}
