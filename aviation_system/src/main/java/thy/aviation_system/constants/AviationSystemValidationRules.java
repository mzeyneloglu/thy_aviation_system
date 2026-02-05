package thy.aviation_system.constants;

import lombok.Getter;

@Getter
public enum AviationSystemValidationRules {

    UNEXPECTED_ERROR("THY-0001", "Unexpected error!"),
    LOCATION_NOT_FOUND("THY-0002", "Location not found!"),
    TRANSPORTATION_NOT_FOUND("THY-0003", "Transportation not found!"),
    LOCATION_NOT_FOUND_BY_CODE("THY-0004", "Location not found by code!"),
    ORIGIN_LOCATION_AND_DESTINATION_LOCATION_CANNOT_BE_SAME("THY-0005","Origin location and destination location cannot be same!"),
    INSERTED_LOCATION("THY-0006","Inserted same location!"),
    INSERTED_TRANSPORTATION("THY-0007", "Inserted same transportation!");

    private final String defaultMessage;
    private final String code;

    AviationSystemValidationRules (String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }
}
