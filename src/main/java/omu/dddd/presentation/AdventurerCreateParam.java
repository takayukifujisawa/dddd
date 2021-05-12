package omu.dddd.presentation;

import lombok.Data;
import omu.dddd.domain.Race;

@Data
public class AdventurerCreateParam {
    String name;
    Race race;
    Integer vitality;
    Integer strength;
    Integer dexterity;
    Integer reflex;
    Integer intelligence;
    Integer wisdom;
    Integer mind;
    Integer agility;
    Integer luck;
}
