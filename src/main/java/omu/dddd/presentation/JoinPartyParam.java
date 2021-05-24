package omu.dddd.presentation;

import javax.validation.constraints.NotNull;

import lombok.Data;
import omu.dddd.domain.validationRule.Required;

@Data
public class JoinPartyParam {

    @NotNull(groups = Required.class)
    private Integer targetPartyId;
    
    @NotNull(groups = Required.class)
    private Integer targetAdventurerId;
}
