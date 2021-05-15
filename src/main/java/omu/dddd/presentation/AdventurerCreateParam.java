package omu.dddd.presentation;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import omu.dddd.domain.Race;
import omu.dddd.domain.validationRule.BusinessLogic;
import omu.dddd.domain.validationRule.FormatCheck;
import omu.dddd.domain.validationRule.Required;

@Data
public class AdventurerCreateParam {

    @NotNull(groups = Required.class)
    @Size(min=1, max=20, groups = FormatCheck.class)
    String name;

    @NotNull(groups = Required.class)
    Race race;

    @NotNull(groups = Required.class)
    Integer vitality;

    @NotNull(groups = Required.class)
    Integer strength;

    @NotNull(groups = Required.class)
    Integer dexterity;

    @NotNull(groups = Required.class)
    Integer reflex;

    @NotNull(groups = Required.class)
    Integer intelligence;

    @NotNull(groups = Required.class)
    Integer wisdom;

    @NotNull(groups = Required.class)
    Integer mind;

    @NotNull(groups = Required.class)
    Integer agility;

    @NotNull(groups = Required.class)
    Integer luck;

    public static final int MAX_TOTAL_PARAMETERS = 45;

    @AssertTrue(message = "追加パラメータの合計が 45 を超えています", groups = BusinessLogic.class)
    boolean isOverMaxTotalParameters() {
        int totalParams = vitality + strength + dexterity + reflex + intelligence + wisdom + mind + agility + luck;
        return totalParams <= MAX_TOTAL_PARAMETERS;
    }
}
