package omu.dddd.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import omu.dddd.presentation.AdventurerCreateParam;

@AllArgsConstructor
@Data
public class Adventurer {
 
    Integer id;
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

    public Adventurer(AdventurerCreateParam param) {
        this.name = param.getName();
        this.race = param.getRace();

        RaceBaseStatus base = RaceBaseStatus.valueOf(this.race.toString());
        this.vitality = param.getVitality() + base.vitalityBase;
        this.strength = param.getStrength() + base.strengthBase;
        this.dexterity = param.getDexterity() + base.dexterityBase;
        this.reflex = param.getReflex() + base.reflexBase;
        this.intelligence = param.getIntelligence() + base.intelligenceBase;
        this.wisdom = param.getWisdom() + base.wisdomBase;
        this.mind = param.getMind() + base.mindBase;
        this.agility = param.getAgility() + base.agilityBase;
        this.luck = param.getLuck() + base.luckBase;
    }

}