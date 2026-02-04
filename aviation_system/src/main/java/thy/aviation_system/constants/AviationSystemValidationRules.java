package thy.aviation_system.constants;

import lombok.Getter;

@Getter
public enum AviationSystemValidationRules {

    LOCATION_NOT_FOUND("THY-0001", "Location not found!"),
    TRANSPORTATION_NOT_FOUND("THY-0002", "Transportation not found!"),
    LOCATION_NOT_FOUND_BY_CODE("THY-0003", "Location not found by code!"),
    ORIGIN_LOCATION_AND_DESTINATION_LOCATION_CANNOT_BE_SAME("THY-0004","Origin location and destination location cannot be same!"),
    INSERTED_LOCATION("THY-0005","Inserted same location!"),
    INSERTED_TRANSPORTATION("THY-0006", "Inserted same transportation!");

    private final String defaultMessage;
    private final String code;

    AviationSystemValidationRules (String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }
}
