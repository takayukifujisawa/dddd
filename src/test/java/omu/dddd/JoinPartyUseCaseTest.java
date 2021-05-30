package omu.dddd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import omu.dddd.application.JoinPartyUseCase;
import omu.dddd.domain.IPartyRepository;
import omu.dddd.domain.PartyMemberDuplicatedException;
import omu.dddd.domain.PartyMembers;
import omu.dddd.presentation.JoinPartyParam;

@SpringBootTest
@Transactional
public class JoinPartyUseCaseTest {
    
    @Autowired
    private JoinPartyUseCase joinPartyUseCase;

    @Autowired
    private IPartyRepository partyRepository;

	@Test
	void testJoin() throws PartyMemberDuplicatedException {

        JoinPartyParam jpp = new JoinPartyParam();
        jpp.setTargetPartyId(1);
        jpp.setTargetAdventurerId(3);

        PartyMembers partyMembersBefore = partyRepository.getPartyMembers(jpp.getTargetPartyId());
        PartyMembers partyMembersAfter = joinPartyUseCase.join(jpp);
         
        assertEquals(partyMembersBefore.menmberCount() + 1, partyMembersAfter.menmberCount());
        assertTrue(partyMembersAfter.contains(jpp.getTargetAdventurerId()));
	}

    @Nested
    class Dupulicate {

        @BeforeEach
        void preJoinAdventurer() throws PartyMemberDuplicatedException {
            JoinPartyParam jpp = new JoinPartyParam();
            jpp.setTargetPartyId(2);
            jpp.setTargetAdventurerId(2);

            joinPartyUseCase.join(jpp);
        }

        @Test
        void testJoinFailDuplicate() {
            Assertions.assertThrows(PartyMemberDuplicatedException.class, () -> {
                JoinPartyParam jpp = new JoinPartyParam();
                jpp.setTargetPartyId(2);
                jpp.setTargetAdventurerId(2);
                joinPartyUseCase.join(jpp);            
            });
        }
    }

}