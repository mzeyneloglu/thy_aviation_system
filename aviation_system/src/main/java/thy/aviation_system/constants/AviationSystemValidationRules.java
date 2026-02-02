package thy.aviation_system.constants;

import lombok.Getter;

@Getter
public enum AviationSystemValidationRules {

    LOCATION_NOT_FOUND("THY-0001", "LOCATION_NOT_FOUND"),
    TRANSPORTATION_NOT_FOUND("THY-0002", "TRANSPORTATION_NOT_FOUND"),
    LOCATION_NOT_FOUND_BY_CODE("THY-0003", "LOCATION_NOT_FOUND_BY_CODE"),
    ORIGIN_LOCATION_AND_DESTINATION_LOCATION_CANNOT_BE_SAME("THY-0004","ORIGIN_LOCATION_AND_DESTINATION_LOCATION_CANNOT_BE_SAME"),
    INSERTED_LOCATION("THY-0004","INSERTED_LOCATION");

    private final String defaultMessage;
    private final String code;

    AviationSystemValidationRules (String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }
}
