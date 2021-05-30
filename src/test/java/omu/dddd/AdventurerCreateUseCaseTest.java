package omu.dddd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import omu.dddd.application.AdventurerCreateUseCase;
import omu.dddd.domain.Adventurer;
import omu.dddd.domain.IAdventurerRepository;
import omu.dddd.domain.Race;
import omu.dddd.domain.RaceBaseStatus;
import omu.dddd.presentation.AdventurerCreateParam;

@SpringBootTest
@Transactional
public class AdventurerCreateUseCaseTest {
    
    @Autowired
    private AdventurerCreateUseCase adventurerCreateUseCase;

    @Autowired
    private IAdventurerRepository adventurerRepository;

	@Test
	void testCreate() {
        AdventurerCreateParam acp = new AdventurerCreateParam();
        acp.setName("test_adventurer");
        acp.setRace(Race.Human);
        acp.setAgility(1);
        acp.setDexterity(2);
        acp.setIntelligence(3);
        acp.setLuck(4);
        acp.setMind(5);
        acp.setReflex(6);
        acp.setStrength(7);
        acp.setVitality(8);
        acp.setWisdom(9);

        Integer newAdventurerId = adventurerCreateUseCase.create(acp).getId();
        assertNotNull(newAdventurerId);

        Adventurer newAdventurer = adventurerRepository.findById(newAdventurerId);

        RaceBaseStatus base = RaceBaseStatus.valueOf(acp.getRace().toString());
        assertEquals(acp.getName(),                                 newAdventurer.getName());
        assertEquals(acp.getRace(),                                 newAdventurer.getRace());
        assertEquals(acp.getVitality() + base.vitalityBase,         newAdventurer.getVitality());
        assertEquals(acp.getStrength() + base.strengthBase,         newAdventurer.getStrength());
        assertEquals(acp.getDexterity() + base.dexterityBase,       newAdventurer.getDexterity());
        assertEquals(acp.getReflex() + base.reflexBase,             newAdventurer.getReflex());
        assertEquals(acp.getIntelligence() + base.intelligenceBase, newAdventurer.getIntelligence());
        assertEquals(acp.getWisdom() + base.wisdomBase,             newAdventurer.getWisdom());
        assertEquals(acp.getMind() + base.mindBase,                 newAdventurer.getMind());
        assertEquals(acp.getAgility() + base.agilityBase ,          newAdventurer.getAgility());
        assertEquals(acp.getLuck() + base.luckBase,                 newAdventurer.getLuck());
	}

}