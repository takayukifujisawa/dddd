package omu.dddd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import omu.dddd.application.LeavePartyUseCase;
import omu.dddd.domain.IPartyRepository;
import omu.dddd.domain.PartyMemberUncontainsException;
import omu.dddd.domain.PartyMembers;
import omu.dddd.presentation.LeavePartyParam;

@SpringBootTest
public class LeavePartyUseCaseTest {
    
    @Autowired
    private LeavePartyUseCase leavePartyUseCase;

    @Autowired
    private IPartyRepository partyRepository;

	@Test
	void testLeave() throws PartyMemberUncontainsException {

        LeavePartyParam lpp = new LeavePartyParam();
        lpp.setTargetPartyId(1);
        lpp.setTargetAdventurerId(2);

        PartyMembers partyMembersBefore = partyRepository.getPartyMembers(lpp.getTargetPartyId());
        PartyMembers partyMembersAfter = leavePartyUseCase.leave(lpp);
         
        assertEquals(partyMembersBefore.menmberCount() - 1, partyMembersAfter.menmberCount());
        assertFalse(partyMembersAfter.contains(lpp.getTargetAdventurerId()));
	}

    @Test
    void testLeaveFailuUncontain() {
        Assertions.assertThrows(PartyMemberUncontainsException.class, () -> {
            LeavePartyParam lpp = new LeavePartyParam();
            lpp.setTargetPartyId(2);
            lpp.setTargetAdventurerId(999);
            leavePartyUseCase.leave(lpp);
        });
    }

}