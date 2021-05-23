package omu.dddd.application;

import org.springframework.stereotype.Service;

import omu.dddd.domain.IPartyRepository;
import omu.dddd.domain.PartyMemberDuplicatedException;
import omu.dddd.domain.PartyMembers;
import omu.dddd.presentation.JoinPartyParam;

@Service
public class JoinPartyUseCase {

    private IPartyRepository partyRepository;

	public JoinPartyUseCase(IPartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    public PartyMembers join(JoinPartyParam jpp) throws PartyMemberDuplicatedException {
        // TODO: 重複エラー実装
        partyRepository.addPartyMember(jpp.getTargetPartyId(), jpp.getTargetAdventurerId());
        return partyRepository.getPartyMembers(jpp.getTargetPartyId());
    }

}
