package omu.dddd.domain.validationRule;

import javax.validation.GroupSequence;

@GroupSequence({Required.class, FormatCheck.class, Conversion.class, BusinessLogic.class})
public interface DefaultOrder {
    
}