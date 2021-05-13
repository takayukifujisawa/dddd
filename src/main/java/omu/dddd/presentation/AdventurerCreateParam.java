package omu.dddd.presentation;

import javax.validation.constraints.NotNull;

import lombok.Data;
import omu.dddd.domain.Race;

@Data
public class AdventurerCreateParam {

    @NotNull
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
}
