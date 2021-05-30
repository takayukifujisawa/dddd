package omu.dddd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import omu.dddd.domain.Adventurer;
import omu.dddd.domain.IAdventurerRepository;

@SpringBootTest
@Transactional
public class AdventurerTest {
    
    @Autowired
    private IAdventurerRepository adventurerRepository;

	@Test
	void test() {
        Adventurer adventurer = adventurerRepository.findById(1);
        assertEquals("冒険者A", adventurer.getName());
	}

}