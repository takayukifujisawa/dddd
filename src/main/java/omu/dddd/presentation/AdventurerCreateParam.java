package omu.dddd.presentation;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import omu.dddd.domain.Race;

@Data
public class AdventurerCreateParam {

    @NotNull
    @Size(min=1, max=20)
    String name;

    @NotNull
    Race race;

    @NotNull
    Integer vitality;

    @NotNull
    Integer strength;

    @NotNull
    Integer dexterity;

    @NotNull
    Integer reflex;

    @NotNull
    Integer intelligence;

    @NotNull
    Integer wisdom;

    @NotNull
    Integer mind;

    @NotNull
    Integer agility;

    @NotNull
    Integer luck;

    public static final int MAX_TOTAL_PARAMETERS = 45;

    @AssertTrue(message = "追加パラメータの合計が 45 を超えています")
    boolean isOverMaxTotalParameters() {
        int totalParams = vitality + strength + dexterity + reflex + intelligence + wisdom + mind + agility + luck;
        return totalParams <= MAX_TOTAL_PARAMETERS;
    }
}
