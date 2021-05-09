package omu.dddd.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Adventurer {
 
    Integer id;
    String name;
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