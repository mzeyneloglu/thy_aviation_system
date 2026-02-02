package thy.aviation_system.exception;

import lombok.Getter;
import lombok.Setter;
import thy.aviation_system.constants.AviationSystemValidationRules;

@Getter
@Setter
public class BusinessValidationException extends RuntimeException {

    private final String code;

    public BusinessValidationException(AviationSystemValidationRules rule) {
        super(rule.getDefaultMessage());
        this.code = rule.getCode();
    }

}
