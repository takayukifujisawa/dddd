package omu.dddd.presentation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import omu.dddd.domain.validationRule.FormatCheck;
import omu.dddd.domain.validationRule.Required;

@Data
public class PartyCreateParam {

    @NotNull(groups = Required.class)
    @Size(min=1, max=20, groups = FormatCheck.class)
    String name;
}
