package thy.aviation_system.constants;

import lombok.Getter;

@Getter
public enum AviationSystemValidationRules {

    LOCATION_NOT_FOUND("THY-0001", "LOCATION_NOT_FOUND"),
    TRANSPORTATION_NOT_FOUND("THY-0002", "TRANSPORTATION_NOT_FOUND");

    private final String defaultMessage;
    private final String code;

    AviationSystemValidationRules (String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }
}
