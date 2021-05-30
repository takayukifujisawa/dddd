package omu.dddd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import omu.dddd.application.PartyCreateUseCase;
import omu.dddd.domain.IPartyRepository;
import omu.dddd.domain.Party;
import omu.dddd.presentation.PartyCreateParam;

@SpringBootTest
@Transactional
public class PartyCreateUseCaseTest {
    
    @Autowired
    private PartyCreateUseCase partyCreateUseCase;

    @Autowired
    private IPartyRepository partyRepository;
	@Test
	void testCreate() {

        PartyCreateParam pcp = new PartyCreateParam();
        pcp.setName("test_Party");

        Integer newPartyId = partyCreateUseCase.create(pcp).getId();
        assertNotNull(newPartyId);

        Party newParty = partyRepository.findById(newPartyId);

        assertEquals(pcp.getName(), newParty.getName());
	}

}