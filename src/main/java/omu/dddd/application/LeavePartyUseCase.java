package omu.dddd.application;

import org.springframework.stereotype.Service;

import omu.dddd.domain.IPartyRepository;
import omu.dddd.domain.PartyMemberUncontainsException;
import omu.dddd.domain.PartyMembers;
import omu.dddd.presentation.LeavePartyParam;

@Service
public class LeavePartyUseCase {
    
    private IPartyRepository partyRepository;

    public LeavePartyUseCase(IPartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    public PartyMembers leave(LeavePartyParam lpp) throws PartyMemberUncontainsException{
        boolean hasNotJoined = partyRepository.getPartyMember(lpp.getTargetPartyId(), lpp.getTargetAdventurerId()) == null;
        if (hasNotJoined) {
            throw new PartyMemberUncontainsException();
        }

        partyRepository.deletePartyMember(lpp.getTargetPartyId(), lpp.getTargetAdventurerId());
        return partyRepository.getPartyMembers(lpp.getTargetPartyId());
    }    
}
